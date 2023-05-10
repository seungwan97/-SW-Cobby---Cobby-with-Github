package com.cobby.main.avatar.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.request.AvatarUpdateRequest;
import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.costume.db.repository.CostumeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

	private final AvatarRepository avatarRepository;

	private final CostumeRepository costumeRepository;

	private final ObjectMapper objectMapper;

	@Override
	public AvatarGetResponse selectAvatar(String avatarId) throws JsonProcessingException {
		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		List<Long> currentCostumes = objectMapper.readValue(
			avatar.getCurrentCostumes(),
			objectMapper.getTypeFactory().constructParametricType(List.class, Long.class));

		List<String> outfits = currentCostumes.stream()
			.map(costumeId -> costumeRepository.findById(costumeId)
				.map((Costume::getGifUrl))
				.orElse(null))
			.toList();

		return AvatarGetResponse.builder()
			.avatar(avatar)
			.outfits(outfits)
			.build();
	}

	@Override
	public String insertDefaultAvatar(String avatarId) {
		avatarRepository.findById(avatarId)
			.ifPresent((x) -> {
				throw new IllegalArgumentException("이미 존재하는 아바타입니다.");
			});

		var newAvatar = getDefaultAvatar(avatarId);

		return avatarRepository.save(newAvatar).getAvatarId();
	}

	@Override
	public String updateAvatar(String avatarId, AvatarUpdateRequest avatarUpdateInfo) throws JsonProcessingException {

		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		List<Long> currentCostumes = objectMapper.readValue(
			avatar.getCurrentCostumes(),
			objectMapper.getTypeFactory().constructParametricType(List.class, Long.class));

		var level = Optional.of(avatarUpdateInfo.level())
			.orElse(avatar.getLevel());
		var exp = Optional.of(avatarUpdateInfo.exp())
			.orElse(avatar.getExp());
		var head = Optional.of(avatarUpdateInfo.head())
			.orElse(currentCostumes.get(CostumeCategory.HEAD.ordinal()));
		var face = Optional.of(avatarUpdateInfo.face())
			.orElse(currentCostumes.get(CostumeCategory.FACE.ordinal()));
		var body = Optional.of(avatarUpdateInfo.body())
			.orElse(currentCostumes.get(CostumeCategory.BODY.ordinal()));

		avatar = avatar.toBuilder()
			.level(level)
			.exp(exp)
			.currentCostumes(objectMapper.writeValueAsString(List.of(head, face, body)))
			.build();

		return avatarRepository.save(avatar).getAvatarId();
	}

	@Override
	public String resetAvatar(String avatarId) {
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

	private Avatar getDefaultAvatar(String userId) {

		return Avatar.builder()
			.avatarId(userId)
			.level(1)
			.exp(0)
			.currentCostumes("[]")
			.build();
	}
}
