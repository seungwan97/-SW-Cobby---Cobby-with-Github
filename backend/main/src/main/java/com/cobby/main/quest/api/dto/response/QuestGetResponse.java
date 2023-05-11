package com.cobby.main.quest.api.dto.response;

import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.title.api.dto.response.TitleGetResponse;
import com.cobby.main.title.db.entity.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestGetResponse {

    private Long questId;

    private String questName;

    private QuestCategory questType;

    private Integer questGoal;

    private CostumeGetResponse costume;

    private TitleGetResponse title;

    @Builder
    public QuestGetResponse(Quest quest) {
        this.questId = quest.getQuestId();
        this.questName = quest.getQuestName();
        this.questType = quest.getQuestType();
        this.questGoal = quest.getQuestGoal();
        this.costume = CostumeGetResponse.builder()
            .costume(quest.getCostume())
            .build();
        this.title = TitleGetResponse.builder()
            .title(quest.getTitle())
            .build();
    }
}
