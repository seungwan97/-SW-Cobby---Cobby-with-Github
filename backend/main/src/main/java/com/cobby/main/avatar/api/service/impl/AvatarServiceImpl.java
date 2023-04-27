package com.cobby.main.avatar.api.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.costume.db.repository.CostumeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

	private final AvatarRepository avatarRepository;

	private final CostumeRepository costumeRepository;

	@Override
	public AvatarGetResponse selectAvatar(String userId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		return AvatarGetResponse.builder()
			.avatar(avatar)
			.build();
	}

	@Override
	public String insertDefaultAvatar(String userId) {
		avatarRepository.findById(userId)
			.ifPresent((x) -> {
				throw new IllegalArgumentException("이미 존재하는 아바타입니다.");
			});

		var newAvatar = getDefaultAvatar(userId);

		return avatarRepository.save(newAvatar).getAvatarId();
	}

	@Override
	public String updateAvatar(String userId, Map<String, Integer> avatarUpdateInfo) {

		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var level = avatarUpdateInfo.containsKey("level") ? avatarUpdateInfo.get("level") : avatar.getLevel();
		var exp = avatarUpdateInfo.containsKey("exp") ? avatarUpdateInfo.get("exp") : avatar.getExp();

		// 이미지 변경하는 로직 추가해야 함

		avatar = avatar.toBuilder()
			.level(level)
			.exp(exp)
			.build();

		return avatarRepository.save(avatar).getAvatarId();
	}

	@Override
	public String resetAvatar(String userId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		return avatarRepository.save(getDefaultAvatar(userId)).getAvatarId();
	}

	@Override
	public String deleteAvatar(String userId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		avatarRepository.deleteById(userId);

		return avatar.getAvatarId();
	}

	private Avatar getDefaultAvatar(String userId) {
		// BaseRuntimeException 으로 throw 해서 Interceptor 에서 처리하고 싶은데
		// AOP 에서 먼저 처리가 되는 이슈가 있음
		// 추후 해결해야 함
		var defaultAvatar = costumeRepository.findAllByCategory(CostumeCategory.DEFAULT).stream()
			.findFirst();
			// .orElseThrow(() -> new IllegalArgumentException("DEFAULT 아바타 정보를 찾을 수 없습니다."));

		return Avatar.builder()
			.avatarId(userId)
			.level(1)
			.exp(0)
			// .avatarImgUrl(defaultAvatar.getImgUrl())
			.build();
	}
}
