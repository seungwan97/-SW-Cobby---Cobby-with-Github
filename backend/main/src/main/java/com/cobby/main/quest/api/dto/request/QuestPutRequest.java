package com.cobby.main.quest.api.dto.request;

import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.title.db.entity.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestPutRequest {

    private Integer questId;

    private String questName;

    private QuestCategory questType;

    private Integer questGoal;

   private List<Costume> costumes = new ArrayList<>();

    private List<Title> titles = new ArrayList<>();
}
