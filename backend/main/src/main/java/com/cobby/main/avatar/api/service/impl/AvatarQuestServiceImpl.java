package com.cobby.main.avatar.api.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.dto.response.AvatarQuestGetResponse;
import com.cobby.main.avatar.api.service.AvatarQuestService;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.avatar.db.entity.AvatarQuest;
import com.cobby.main.avatar.db.repository.AvatarQuestRepository;
import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.common.exception.BaseRuntimeException;
import com.cobby.main.quest.api.service.QuestService;
import com.cobby.main.quest.db.repository.QuestRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class AvatarQuestServiceImpl implements AvatarQuestService {

	private final AvatarQuestRepository avatarQuestRepository;
	private final QuestRepository questRepository;
	private final AvatarRepository avatarRepository;

	@Override
	public Long insertItem(String avatarId, AvatarItemPostRequest itemInfo) {
		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		var quest = questRepository.findById(itemInfo.itemId())
			.orElseThrow(() -> new IllegalArgumentException("아이템 정보가 없습니다. (type=" +
				itemInfo.itemType() + ", ID=" + itemInfo.itemId() + ")"));

		avatarQuestRepository.findByAvatar_AvatarIdAndQuest_QuestId(avatarId, quest.getQuestId())
			.ifPresent((x) -> {
				throw new IllegalArgumentException("이미 클리어한 퀘스트입니다. (ID=" + quest.getQuestId() + ")");});

		var avatarQuest = AvatarQuest.builder()
			.avatar(avatar)
			.quest(quest)
			.build();

		return avatarQuestRepository.save(avatarQuest).getAvatarQuestId();
	}

	@Override
	public List<AvatarQuestGetResponse> selectAllItems(String userId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var avatarQuests = avatarQuestRepository.findAllByAvatar_AvatarId(userId);

		return avatarQuests.stream()
			.map(quest ->
				AvatarQuestGetResponse.builder()
					.quest(quest.getQuest())
					.build())
			.toList();
	}

	// @KafkaListener(topics = "activity-update")

	@Override
	public AvatarQuestGetResponse selectItem(String userId, Long itemId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var avatarQuest = avatarQuestRepository.findById(itemId)
			.orElseThrow(() -> new BaseRuntimeException(HttpStatus.NO_CONTENT, "퀘스트 정보가 없습니다. (ID=" + itemId + ")"));

		return AvatarQuestGetResponse.builder()
			.quest(avatarQuest.getQuest())
			.build();
	}

}
