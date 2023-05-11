package com.cobby.main.quest.api.dto.response;

import java.util.Map;
import java.util.Objects;

import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.title.api.dto.response.TitleGetResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestGetResponse {

	private Long questId;

	private String questName;

	private QuestCategory questType;

	private Integer questGoal;

	private Map<String, Object> costume;

	private Map<String, Object> title;

	@Builder
	public QuestGetResponse(Quest quest) {
		this.questId = quest.getQuestId();
		this.questName = quest.getQuestName();
		this.questType = quest.getQuestType();
		this.questGoal = quest.getQuestGoal();
		var costume = quest.getCostume();
		this.costume = Objects.isNull(quest.getCostume()) ? null :
			Map.of(
				"costumeId", costume.getCostumeId(),
				"name", costume.getName(),
				"category", costume.getCategory().name(),
				"imgUrl", costume.getImgUrl(),
				"gifUrl", costume.getGifUrl()
			);
		var title = quest.getTitle();
		this.title = Objects.isNull(quest.getTitle()) ? null :
			Map.of(
				"titleId", title.getTitleId(),
				"name", title.getName(),
				"explanation", title.getExplanation()
			);
	}
}
