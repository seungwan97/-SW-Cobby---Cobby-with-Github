package com.cobby.main.quest.db.entity;

import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.title.db.entity.Title;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quest {

	@Positive(message = "양수 값이어야 합니다. ")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, columnDefinition = "INT UNSIGNED")
	private Long questId;

	@NotBlank(message = "필수 입력 사항입니다. ")
	private String questName;

	@NotBlank(message = "필수 입력 사항입니다. ")
	@Enumerated(EnumType.STRING)
	private QuestCategory questType;

	@Positive(message = "양수 값이어야 합니다. ")
	@Column(nullable = false, columnDefinition = "INT UNSIGNED")
	private Integer questGoal;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "costume_id")
	@JsonBackReference
	private Costume costume;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title_id")
	@JsonBackReference
	private Title title;

	@Builder(toBuilder = true)
	public Quest(String questName, QuestCategory questType, Integer questGoal, Costume costume, Title title) {
		Assert.hasText(questName, "필수 입력 항목힙니다. ");
		Assert.isTrue(0 <
			Arrays.stream(QuestCategory.values())
				.filter(questCategory -> questCategory.equals(questType))
				.toArray()
				.length, "COMMIT/ATTENDANCE/LEVEL/ITEM 중 하나여야 합니다. ");
		Assert.isTrue(0 < questGoal, "1 이상의 값이어야 합니다. ");

		this.questName = questName;
		this.questType = questType;
		this.questGoal = questGoal;
		this.costume = costume;
		this.title = title;
	}
}
