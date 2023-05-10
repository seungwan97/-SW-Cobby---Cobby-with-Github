package com.cobby.main.quest.db.entity;

import com.cobby.main.quest.db.entity.enumtype.QuestCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentQuest{
	private Long questId;

	private String questName;

	private QuestCategory questType;

	// quest code
	private Integer questGoal;

	// 진행도
	private Integer progress;

	private Object award;
}
