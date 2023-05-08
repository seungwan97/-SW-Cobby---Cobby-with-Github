package com.cobby.main.quest.db.entity;

import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
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
	private Long questId;

	private String questName;

	@Enumerated(EnumType.STRING)
	private QuestCategory questType;

	@Column(nullable = false, columnDefinition="INT UNSIGNED")
	private Integer questGoal;

	@OneToMany(mappedBy = "quest")
	private List<Costume> costumes = new ArrayList<>();

	@OneToMany(mappedBy = "quest")
	private List<Title> titles = new ArrayList<>();
}
