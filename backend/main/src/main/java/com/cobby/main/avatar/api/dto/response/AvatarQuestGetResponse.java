package com.cobby.main.avatar.api.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.cobby.main.avatar.db.entity.AvatarQuest;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.title.db.entity.Title;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvatarQuestGetResponse {

	private Integer questId;

	private String questName;

	private QuestCategory questType;

	// quest code
	private Integer questGoal;

	// 진행도
	private Integer progress;

	// private Costume awardCostume;
	//
	// private Title awardTitle;

	// costume or title
	private Object award;

	// @Builder
	// public AvatarQuestGetResponse(AvatarQuest avatarQuest) {
	// 	this.questId = avatarQuest.getAvatarQuestId();
	// 	this.questName = avatarQuest.getQuest().getQuestName();
	// 	this.questGoal = avatarQuest.getQuest().getQuestGoal();
	// }

}
