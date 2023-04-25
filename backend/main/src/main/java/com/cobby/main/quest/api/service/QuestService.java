package com.cobby.main.quest.api.service;

import java.util.List;

import com.cobby.main.quest.db.entity.Quest;

public interface QuestService {

	List<Quest> selectAllQuest();

}
