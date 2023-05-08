package com.cobby.main.avatar.api.service;

import java.util.List;

import com.cobby.main.avatar.api.dto.response.AvatarQuestGetResponse;
import com.cobby.main.avatar.api.dto.response.CurrentQuest;

public interface AvatarQuestService extends AvatarItemService<AvatarQuestGetResponse> {
	List<CurrentQuest> selectAvatarCurrentQuests(String avatarId);
}
