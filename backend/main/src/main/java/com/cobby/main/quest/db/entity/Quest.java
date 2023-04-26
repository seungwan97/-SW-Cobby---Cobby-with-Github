package com.cobby.main.quest.db.entity;

import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.title.db.entity.Title;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Quest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, columnDefinition="INT UNSIGNED")
	private int questId;

	private String questName;

	private char questType;	// ENUM?

	@Column(nullable = false, columnDefinition="INT UNSIGNED")
	private int questCode;

//	@OneToMany(mappedBy = "quest")
//	private List<Costume> costumes = new ArrayList<>();

	@OneToMany(mappedBy = "quest")
	private List<Title> titles = new ArrayList<>();

	// Entity to Dto
	public QuestGetResponse toDto() {return new QuestGetResponse(questId, questName, questType, questCode, titles);}
}
