package com.cobby.main.avatar.api.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.request.AvatarCostumePatchRequest;
import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.dto.response.AvatarCostumeGetResponse;
import com.cobby.main.avatar.api.service.AvatarCostumeService;
import com.cobby.main.avatar.db.entity.AvatarCostume;
import com.cobby.main.avatar.db.repository.AvatarCostumeRepository;
import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.common.exception.BaseRuntimeException;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.costume.db.repository.CostumeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class AvatarCostumeServiceImpl implements AvatarCostumeService {
	private final AvatarRepository avatarRepository;
	private final CostumeRepository costumeRepository;
	private final AvatarCostumeRepository avatarCostumeRepository;

	@Override
	public Long insertItem(String avatarId, AvatarItemPostRequest itemInfo) {
		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		var costume = costumeRepository.findById(itemInfo.itemId())
			.orElseThrow(() -> new IllegalArgumentException("아이템 정보가 없습니다. (type=" +
				itemInfo.itemType() + ", ID=" + itemInfo.itemId() + ")"));

		avatarCostumeRepository.findByAvatar_AvatarIdAndCostume_CostumeId(avatarId, costume.getCostumeId())
			.ifPresent((x) -> {
				throw new IllegalArgumentException("이미 보유하고 있는 코스튬입니다. (ID=" + costume.getCostumeId() + ")");});

		var avatarCostume = AvatarCostume.builder()
			.avatar(avatar)
			.costume(costume)
			.isOpened(false)
			.build();

		return avatarCostumeRepository.save(avatarCostume).getAvatarCostumeId();
	}

	@Override
	public List<AvatarCostumeGetResponse> selectAllItems(String userId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var avatarCostumes = avatarCostumeRepository.findAllByAvatar_AvatarId(userId);

		return avatarCostumes.stream()
			.map(costume ->
				AvatarCostumeGetResponse.builder()
					.costume(costume.getCostume())
					.build())
			.toList();
	}

	@Override
	public AvatarCostumeGetResponse selectItem(String userId, Long itemId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var avatarCostume = avatarCostumeRepository.findById(itemId)
			.orElseThrow(() -> new BaseRuntimeException(HttpStatus.NO_CONTENT, "코스튬 정보가 없습니다. (ID=" + itemId + ")"));

		return AvatarCostumeGetResponse.builder()
			.costume(avatarCostume.getCostume())
			.build();
	}

	@Override
	public List<AvatarCostumeGetResponse> selectAllCostumesByType(String userId, String category) {

		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var avatarCostumes = avatarCostumeRepository.findAllByAvatar_AvatarIdAndCostume_Category(avatar.getAvatarId(), CostumeCategory.valueOf(category));

		return avatarCostumes.stream()
			.map(costume ->
				AvatarCostumeGetResponse.builder()
					.costume(costume.getCostume())
					.build())
			.toList();
	}

	@Override
	public Long openItem(String userId, AvatarCostumePatchRequest costumeInfo) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var avatarCostume = avatarCostumeRepository.findByAvatar_AvatarIdAndCostume_CostumeId(userId, costumeInfo.costumeId())
			.orElseThrow(() -> new IllegalArgumentException("코스튬 정보가 없습니다. (ID=" + costumeInfo.costumeId() + ")"));

		avatarCostume = avatarCostume.toBuilder()
			.isOpened(true)
			.build();

		return avatarCostumeRepository.save(avatarCostume).getCostume().getCostumeId();
	}
}
