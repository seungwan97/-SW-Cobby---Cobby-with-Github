package com.cobby.main.quest.api.dto.response;

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
public class QuestGetResponse {

    private Integer questId;

    private String questName;

    private QuestCategory questType;

    private Integer questCode;

    private List<Costume> costumes = new ArrayList<>();

    private List<Title> titles = new ArrayList<>();
}
