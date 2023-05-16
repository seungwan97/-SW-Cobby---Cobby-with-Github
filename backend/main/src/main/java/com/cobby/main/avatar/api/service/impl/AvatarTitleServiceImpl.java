package com.cobby.main.avatar.api.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.dto.response.AvatarTitleGetResponse;
import com.cobby.main.avatar.api.service.AvatarTitleService;
import com.cobby.main.avatar.db.entity.AvatarTitle;
import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.avatar.db.repository.AvatarTitleRepository;
import com.cobby.main.common.exception.BaseRuntimeException;
import com.cobby.main.title.db.repository.TitleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class AvatarTitleServiceImpl implements AvatarTitleService {

	private final AvatarRepository avatarRepository;
	private final TitleRepository titleRepository;
	private final AvatarTitleRepository avatarTitleRepository;

	@Override
	public Long insertItem(String userId, AvatarItemPostRequest itemInfo) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var title = titleRepository.findById(itemInfo.itemId())
			.orElseThrow(() -> new IllegalArgumentException("아이템 정보가 없습니다. (type=" +
				itemInfo.itemType() + ", ID=" + itemInfo.itemId() + ")"));

		var avatarTitle = AvatarTitle.builder()
			.avatar(avatar)
			.title(title)
			.build();

		return avatarTitleRepository.save(avatarTitle).getAvatarTitleId();
	}

	@Override
	public List<AvatarTitleGetResponse> selectAllItems(String avatarId) {
		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		var avatarTitles = avatarTitleRepository.findAllByAvatar_AvatarId(avatarId);

		return avatarTitles.stream()
			.map(title ->
				AvatarTitleGetResponse.builder()
					.title(title.getTitle())
					.build())
			.toList();
	}

	@Override
	public AvatarTitleGetResponse selectItem(String userId, Long itemId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var avatarTitle = avatarTitleRepository.findById(itemId)
			.orElseThrow(() -> new BaseRuntimeException(HttpStatus.NO_CONTENT, "칭호 정보가 없습니다. (ID=" + itemId + ")"));

		return AvatarTitleGetResponse.builder()
			.title(avatarTitle.getTitle())
			.build();
	}
}
