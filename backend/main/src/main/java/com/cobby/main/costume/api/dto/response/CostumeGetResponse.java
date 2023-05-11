package com.cobby.main.costume.api.dto.response;

import java.util.Map;

import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.db.entity.Quest;

import lombok.Builder;
import lombok.Data;

@Data
public class CostumeGetResponse {

	private Long costumeId;

	private String name;

	private CostumeCategory category;

	private Map<String, Object> quest;

	private String imgUrl;

	private String gifUrl;

	@Builder
	public CostumeGetResponse(Costume costume) {
		this.costumeId = costume.getCostumeId();
		this.name = costume.getName();
		this.category = costume.getCategory();
		var quest = costume.getQuest();
		this.quest = Map.of(
			"questId", quest.getQuestId(),
			"questName", quest.getQuestName(),
			"questType", quest.getQuestType().name(),
			"questGoal", quest.getQuestGoal()
		);
		this.imgUrl = costume.getImgUrl();
		this.gifUrl = costume.getGifUrl();
	}

}
