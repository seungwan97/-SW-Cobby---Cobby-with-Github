package com.cobby.main.avatar.api.service.impl;

import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.service.AvatarInventoryService;
import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.avatar.db.entity.AvatarCostume;
import com.cobby.main.avatar.db.entity.AvatarQuest;
import com.cobby.main.avatar.db.entity.AvatarTitle;
import com.cobby.main.avatar.db.repository.AvatarCostumeRepository;
import com.cobby.main.avatar.db.repository.AvatarQuestRepository;
import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.avatar.db.repository.AvatarTitleRepository;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.repository.CostumeRepository;
import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.repository.QuestRepository;
import com.cobby.main.title.db.entity.Title;
import com.cobby.main.title.db.repository.TitleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AvatarInventoryServiceImpl implements AvatarInventoryService {

	private final AvatarCostumeRepository avatarCostumeRepository;
	private final AvatarTitleRepository avatarTitleRepository;
	private final AvatarQuestRepository avatarQuestRepository;
	private final AvatarRepository avatarRepository;
	private final CostumeRepository costumeRepository;
	private final TitleRepository titleRepository;
	private final QuestRepository questRepository;


	@Transactional
	@Override
	public Integer insertAvatarInventoryItem(String userId, AvatarItemPostRequest itemInfo) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		return saveInventoryItem(avatar, itemInfo);
	}

	private Integer saveInventoryItem(Avatar avatar, AvatarItemPostRequest itemInfo) {
		var optional = switch(itemInfo.itemType()) {
			case "costume" -> costumeRepository.findById(itemInfo.itemId());
			case "title" -> titleRepository.findById(itemInfo.itemId());
			case "quest" ->  questRepository.findById(itemInfo.itemId());
		};

		var item = optional.orElseThrow(() ->
			new IllegalArgumentException("아이템 정보가 없습니다. (type=" +
				itemInfo.itemType() + ", ID=" + itemInfo.itemId() + ")"));

		return switch (itemInfo.itemType()) {
			case "costume" -> this.avatarCostumeRepository.save(
				AvatarCostume.builder().avatar(avatar).costume((Costume)item).build()
			).getAvatarCostumeId();
			case "title" -> this.avatarTitleRepository.save(
				AvatarTitle.builder().avatar(avatar).title((Title)item).build()
			).getAvatarTitleId();
			case "quest" -> this.avatarQuestRepository.save(
				AvatarQuest.builder().avatar(avatar).quest((Quest)item).build()
			).getAvatarQuestId();
		};
	}
}
