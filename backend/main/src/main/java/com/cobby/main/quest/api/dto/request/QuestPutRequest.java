package com.cobby.main.quest.api.dto.request;

import java.util.List;

import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.title.db.entity.Title;

import io.swagger.v3.oas.annotations.media.Schema;

public record QuestPutRequest(
    @Schema(description = "도전과제 ID", example = "1")
    Long questId,

    @Schema(description = "도전과제 이름", example = "출석 연속 10회 달성")
    String questName,

    @Schema(description = "도전과제 종류", example = "ATTEND")
    QuestCategory questType,

    @Schema(description = "도전과제 달성 조건", example = "10")
    Integer questGoal,

    @Schema(description = "보상 코스튬 목록")
    List<Costume> costumes,

    @Schema(description = "보상 칭호 목록")
    List<Title> titles
) {

}
