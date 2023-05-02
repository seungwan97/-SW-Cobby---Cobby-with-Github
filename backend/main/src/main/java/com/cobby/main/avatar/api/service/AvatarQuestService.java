package com.cobby.main.avatar.api.service;

import java.util.List;

import com.cobby.main.avatar.db.entity.AvatarQuest;

public interface AvatarQuestService {
	List<AvatarQuest> selectAllAvatarQuests(String userId);
}
