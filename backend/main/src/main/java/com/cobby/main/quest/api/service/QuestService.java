package com.cobby.main.quest.api.service;

import java.util.List;

import com.cobby.main.quest.db.entity.CurrentQuest;
import com.cobby.main.quest.api.dto.request.QuestPostRequest;
import com.cobby.main.quest.api.dto.request.QuestPutRequest;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;

public interface QuestService {

	QuestGetResponse selectQuest(Long questId);
	List<QuestGetResponse> selectAllQuest();
	List<QuestGetResponse> selectAllQuestByQuestType(QuestCategory questCategory);
	void insertQuest(QuestPostRequest questInfo);
	Long updateQuest(QuestPutRequest questInfo);
	Long deleteQuest(Long questId);

	List<CurrentQuest> selectCurrentQuests(String avatarId);
}
