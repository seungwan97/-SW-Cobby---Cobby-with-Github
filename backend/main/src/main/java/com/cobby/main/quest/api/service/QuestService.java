package com.cobby.main.quest.api.service;

import java.util.List;

import com.cobby.main.quest.api.dto.request.QuestPostRequest;
import com.cobby.main.quest.api.dto.request.QuestPutRequest;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.avatar.api.dto.response.AvatarQuestGetResponse;

public interface QuestService {

	QuestGetResponse selectQuest(Integer questId);
	AvatarQuestGetResponse selectUserQuest(Integer userId);
	List<QuestGetResponse> selectAllQuest();
	void insertQuest(QuestPostRequest questInfo);
	void updateQuest(QuestPutRequest questInfo);
	void deleteQuest(Integer questId);
}
