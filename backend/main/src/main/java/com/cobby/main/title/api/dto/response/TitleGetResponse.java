package com.cobby.main.title.api.dto.response;

import com.cobby.main.quest.db.entity.Quest;
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

    @Schema(description = "관련된 도전과제 ID", example = "1")
    private Long questId;

    @Schema(description = "칭호 이름", example = "핫이슈")
    private String name;

    @Schema(description = "칭호 설명", example = "제시된 이슈 총 합계 100개 이상")
    private String explanation;

    @Builder
    public TitleGetResponse(Title title) {
        this.titleId = title.getTitleId();
        this.questId = title.getQuest().getQuestId();
        this.name = title.getName();
        this.explanation = title.getExplanation();
    }
}
