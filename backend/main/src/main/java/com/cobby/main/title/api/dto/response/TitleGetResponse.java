package com.cobby.main.title.api.dto.response;

import java.util.Map;
import java.util.Objects;

import com.cobby.main.title.db.entity.Title;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitleGetResponse {

	@Schema(description = "칭호 ID", example = "1")
	private Long titleId;

	@Schema(description = "관련된 도전과제 정보", type = "Quest")
	private Map<String, Object> quest;

	@Schema(description = "칭호 이름", example = "핫이슈")
	private String name;

	@Schema(description = "칭호 설명", example = "제시된 이슈 총 합계 100개 이상")
	private String explanation;

	@Builder
	public TitleGetResponse(Title title) {
		this.titleId = title.getTitleId();
		var quest = title.getQuest();
		this.quest = Objects.nonNull(title.getQuest()) ? Map.of(
			"questId", quest.getQuestId(),
			"questName", quest.getQuestName(),
			"questType", quest.getQuestType().name(),
			"questGoal", quest.getQuestGoal()
		) : null;
		this.name = title.getName();
		this.explanation = title.getExplanation();
	}

}
