package com.cobby.main.avatar.api.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.request.AvatarPatchRequest;
import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.cobby.main.avatar.api.enumtype.ExpReward;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.avatar.db.repository.AvatarCostumeRepository;
import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.avatar.db.repository.LevelTableRepository;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

	private final AvatarRepository avatarRepository;

	private final LevelTableRepository levelTableRepository;

	private final ObjectMapper objectMapper;

	private final AvatarCostumeRepository avatarCostumeRepository;

	@Transactional
	@Override
	public AvatarGetResponse selectAvatar(String avatarId) throws JsonProcessingException {

		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));


		var outfits = getCostumeOutfits(avatarId, avatar.getOutfits());

		var levelTable = levelTableRepository.findTopByNextExpIsLessThanEqualOrderByLevelDesc(avatar.getExp())
			.orElseThrow(() -> new IllegalArgumentException("레벨 정보가 없습니다. (Level=" + avatar.getLevel() + ")"));

		return AvatarGetResponse.builder()
			.prevExp(levelTable.getPrevExp())
			.nextExp(levelTable.getNextExp())
			.avatar(avatar)
			.outfits(outfits)
			.build();
	}

	private Map<String, CostumeGetResponse> getCostumeOutfits(String avatarId, final String idMapString) throws JsonProcessingException {
		// String 을 Map 으로 변환
		Map<String, Long> idMap = objectMapper.readValue(
			idMapString,
			objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Long.class)
		);

		var NO_COSTUME = 0L;
		var outfits = new HashMap<String, CostumeGetResponse>();

		// 맵에 들어있는 key (costume의 카테고리) value 들 (costume id)을 순회하며
		idMap.forEach((category, costumeId) -> {
			// 코스튬 Id가 0인 경우 "empty" 라는 이름을 가진 빈 객체를 DTO 형태로 outfits map에 넣습니다.
			if (NO_COSTUME == costumeId) {
				var emptyCostume = Costume.builder()
					.costumeId(0L)
					.name("empty")
					.category(CostumeCategory.valueOf(category.toUpperCase()))
					.quest(null)
					.imgUrl("")
					.gifUrl("")
					.build();

				outfits.put(
					category,
					CostumeGetResponse.builder()
						.costume(emptyCostume)
						.build());
			}
			// 코스튬 Id가 0 이상인 경우에는 해당 ID를 가진 코스튬을 넣습니다.
			else {
				var costume = avatarCostumeRepository.findByAvatar_AvatarIdAndCostume_CostumeId(avatarId, costumeId)
					.orElseThrow(() -> new IllegalArgumentException(
						"보유하고 있지 않은 코스튬입니다. (category=" + category + ", ID=" + costumeId + ")"))
					.getCostume();

				outfits.put(
					category,
					CostumeGetResponse.builder()
						.costume(costume)
						.build()
				);
			}
		});

		return outfits;
	}

	@Transactional
	@KafkaListener(topics = "create-new-avatar")
	public String insertNewAvatar(String kafkaMessage) throws JsonProcessingException {

		log.info("create-new-avatar topic message income ==> " + kafkaMessage);
		// Map 형태로 kafka 메시지를 파싱
		Map<String, String> userinfo = objectMapper.readValue(
			kafkaMessage,
			objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, String.class)
		);

		// 사용자 ID 조회
		var avatarId = userinfo.get("userId");

		// 아바타가 존재하면 예외 발생
		avatarRepository.findById(avatarId)
			.ifPresent((x) -> {
				throw new IllegalArgumentException("이미 존재하는 아바타입니다.");
			});

		// 기본 아바타 생성
		var newAvatar = getDefaultAvatar(avatarId);

		// 사용자의 기존 커밋 횟수 조회
		var commitCnt = Long.parseLong(userinfo.get("commitCnt"));

		// (기존 커밋 횟수 X 커밋 보상 경험치 X 0.5) 만큼의 경험치를 제공합니다.
		var initExp = Math.round(commitCnt * ExpReward.COMMIT.getValue() * 0.5);

		newAvatar = addInitialExp(newAvatar, initExp);

		log.info("new avatar created => lv:" + newAvatar.getLevel() + " , exp:" + newAvatar.getExp());

		return avatarRepository.save(newAvatar).getAvatarId();
	}

	private Avatar addInitialExp(Avatar newAvatar, Long initExp) {

		var initLevel = levelTableRepository.findTopByNextExpIsLessThanEqualOrderByLevelDesc(initExp.intValue())
			.orElseThrow(() -> new IllegalArgumentException("초기 경험치보다 작은 레벨이 존재하지 않습니다."));

		return newAvatar.toBuilder()
			.level(initLevel.getLevel())
			.exp(initExp.intValue())
			.build();
	}

	@Transactional
	@Override
	public String updateAvatar(String avatarId, AvatarPatchRequest avatarUpdateInfo) throws JsonProcessingException {

		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		Map<String, Long> currentCostumeIds = objectMapper.readValue(
			avatar.getOutfits(),
			objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Long.class));

		// 각 코스튬 카테고리별로 변화를 감지하고 Map 형태로 저장
		var outfit = Map.of(
			"head", Optional.ofNullable(avatarUpdateInfo.head())
				.orElse(currentCostumeIds.get("head")),
			"body", Optional.ofNullable(avatarUpdateInfo.body())
				.orElse(currentCostumeIds.get("body")),
			"effect", Optional.ofNullable(avatarUpdateInfo.effect())
				.orElse(currentCostumeIds.get("effect"))
		);
		var NO_COSTUME = 0L;
		// 변경하려는 코스튬이 실제로 아바타가 가진 코스튬인지, 또는 빈 값(0)인지 확인
		outfit.forEach((category, costumeId) -> {
			if (NO_COSTUME != costumeId)
				avatarCostumeRepository.findByAvatar_AvatarIdAndCostume_CostumeId(avatarId, costumeId)
					.orElseThrow(() -> new IllegalArgumentException(
						"보유하고 있지 않은 코스튬입니다. (category=" + category + ", ID=" + costumeId + ")"));
		});

		// Map 객체를 String 으로 변환하여 저장
		avatar = avatar.toBuilder()
			.outfits(objectMapper.writeValueAsString(outfit))
			.build();

		// db 저장
		return avatarRepository.save(avatar).getAvatarId();
	}

	@Transactional
	@KafkaListener(topics = "exp-update")
	public void updateAvatarExp(String kafkaMessage) throws JsonProcessingException {
		Map<String, String> activityLog = objectMapper.readValue(
			kafkaMessage,
			objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, String.class)
		);

		// 경험치 보상을 받을 사용자가 존재하는 지 확인
		var avatar = avatarRepository.findById(activityLog.get("userId"))
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + activityLog.get("userId") + ")"));

		// 활동에 따른 (커밋, 출석) 경험치 보상
		var expReward = ExpReward.valueOf(activityLog.get("type")).getValue();

		var updatedExp = avatar.getExp() + expReward;
		var levelInfo = levelTableRepository.findTopByNextExpIsLessThanEqualOrderByLevelDesc(updatedExp)
			.orElseThrow(NotFoundException::new);

		// 경험치 변동에 따흔 아바타 정보 업데이트
		avatar = avatar.toBuilder()
			.exp(updatedExp)
			.level(levelInfo.getLevel())
			.build();

		avatarRepository.save(avatar);
	}

	@Override
	public String resetAvatar(String avatarId) throws JsonProcessingException {
		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		return avatarRepository.save(getDefaultAvatar(avatarId)).getAvatarId();
	}

	@Override
	public String deleteAvatar(String avatarId) {
		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		avatarRepository.deleteById(avatarId);

		return avatar.getAvatarId();
	}

	private Avatar getDefaultAvatar(String userId) throws JsonProcessingException {

		var deafaultOutfits = Map.of(
			"head", CostumeCategory.NO_COSTUME.ordinal(),
			"body", CostumeCategory.NO_COSTUME.ordinal(),
			"effect", CostumeCategory.NO_COSTUME.ordinal()
		);

		return Avatar.builder()
			.avatarId(userId)
			.level(1)
			.exp(0)
			.outfits(objectMapper.writeValueAsString(deafaultOutfits))
			.build();
	}
}
