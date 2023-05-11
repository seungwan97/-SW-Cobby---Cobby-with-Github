package com.cobby.main.avatar.api.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.request.AvatarPatchRequest;
import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.avatar.db.repository.AvatarCostumeRepository;
import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.costume.db.repository.CostumeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

	private final AvatarRepository avatarRepository;

	private final CostumeRepository costumeRepository;

	private final ObjectMapper objectMapper;
	private final AvatarCostumeRepository avatarCostumeRepository;

	@Override
	public AvatarGetResponse selectAvatar(String avatarId) throws JsonProcessingException {
		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		// String 을 Map 으로 변환
		Map<String, Long> map = objectMapper.readValue(
			avatar.getOutfits(),
			objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Long.class)
		);

		var NO_COSTUME = "";
		var outfits = new HashMap<String, String>();

		map.forEach((costumeCategory, costumeId) ->
			outfits.put(costumeCategory, costumeId == 0L ? NO_COSTUME :
				avatarCostumeRepository.findByCostume_CostumeId(costumeId)
					.orElseThrow(() -> new IllegalArgumentException(
						"보유하고 있지 않은 코스튬입니다. (category=" + costumeCategory + ", ID=" + costumeId + ")"))
					.getCostume()
					.getGifUrl()));

		return AvatarGetResponse.builder()
			.avatar(avatar)
			.outfits(outfits)
			.build();
	}

	@Override
	public String insertDefaultAvatar(String avatarId) throws JsonProcessingException {
		avatarRepository.findById(avatarId)
			.ifPresent((x) -> {
				throw new IllegalArgumentException("이미 존재하는 아바타입니다.");
			});

		var newAvatar = getDefaultAvatar(avatarId);

		return avatarRepository.save(newAvatar).getAvatarId();
	}

	// @KafkaListener(topics = "make-avatar")
	// public String saveDefaultAvatar(String avatarId) throws JsonProcessingException {
	// 	log.info("Massage received ===> " + avatarId);
	//
	// 	// 아바타가 존재하면 예외 발생
	// 	avatarRepository.findById(avatarId)
	// 		.ifPresent((x) -> {
	// 			throw new IllegalArgumentException("이미 존재하는 아바타입니다.");
	// 		});
	//
	// 	var newAvatar = getDefaultAvatar(avatarId);
	//
	// 	return avatarRepository.save(newAvatar).getAvatarId();
	// }

	@Override
	public String updateAvatar(String avatarId, AvatarPatchRequest avatarUpdateInfo) throws JsonProcessingException {

		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		Map<String, Long> currentCostumeIds = objectMapper.readValue(
			avatar.getOutfits(),
			objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Long.class));

		var head = Optional.ofNullable(avatarUpdateInfo.head())
			.orElse(currentCostumeIds.get("head"));
		var body = Optional.ofNullable(avatarUpdateInfo.face())
			.orElse(currentCostumeIds.get("body"));
		var effect = Optional.ofNullable(avatarUpdateInfo.body())
			.orElse(currentCostumeIds.get("effect"));

		avatar = avatar.toBuilder()
			.outfits(objectMapper.writeValueAsString(
				Map.of("head", head, "body", body, "effect", effect)))
			.build();

		return avatarRepository.save(avatar).getAvatarId();
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
