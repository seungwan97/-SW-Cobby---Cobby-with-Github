package com.cobby.main.title.api.dto.response;

import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.title.db.entity.Title;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitleGetResponse {

    private Integer titleId;

    private Integer questId;

    private String name;

    private String explanation;

    @Builder
    TitleGetResponse(Title title) {
        this.titleId = title.getTitleId();
        this.questId = title.getQuest().getQuestId();
        this.name = title.getName();
        this.explanation = title.getExplanation();
    }
}
