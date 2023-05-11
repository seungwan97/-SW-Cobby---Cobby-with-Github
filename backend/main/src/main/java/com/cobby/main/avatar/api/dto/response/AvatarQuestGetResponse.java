package com.cobby.main.avatar.api.dto.response;

import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.title.api.dto.response.TitleGetResponse;

import lombok.Builder;
import lombok.Data;

@Data
public class AvatarQuestGetResponse {

	private Long questId;

	private String questName;

	private String questType;

	private Integer questGoal;

	private Long costumeId;

	private Long titleId;

	@Builder
	public AvatarQuestGetResponse(Quest quest) {
		this.questId = quest.getQuestId();
		this.questName = quest.getQuestName();
		this.questType = quest.getQuestType().name();
		this.questGoal = quest.getQuestGoal();
		this.costumeId = quest.getCostume().getCostumeId();
		this.titleId = quest.getTitle().getTitleId();
	}

}
