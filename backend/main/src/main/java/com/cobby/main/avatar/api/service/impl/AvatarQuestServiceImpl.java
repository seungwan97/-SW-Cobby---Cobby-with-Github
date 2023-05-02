package com.cobby.main.avatar.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.service.AvatarQuestService;
import com.cobby.main.avatar.db.entity.AvatarQuest;
import com.cobby.main.avatar.db.repository.AvatarQuestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvatarQuestServiceImpl implements AvatarQuestService {

	private final AvatarQuestRepository avatarQuestRepository;

	@Override
	public List<AvatarQuest> selectAllAvatarQuests(String userId) {
		return avatarQuestRepository.findAllByAvatar_AvatarId(userId);
	}
}
