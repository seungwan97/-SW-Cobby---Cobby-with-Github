package com.cobby.main.avatar.api.dto.response;

import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.title.api.dto.response.TitleGetResponse;
import com.cobby.main.title.db.entity.Title;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class AvatarTitleGetResponse {

	private Long titleId;

	private Long questId;

	private String name;

	private String explanation;

	@Builder
	public AvatarTitleGetResponse(Title title) {
		this.titleId = title.getTitleId();
		this.questId = title.getQuest().getQuestId();
		this.name = title.getName();
		this.explanation = title.getExplanation();
	}
}
