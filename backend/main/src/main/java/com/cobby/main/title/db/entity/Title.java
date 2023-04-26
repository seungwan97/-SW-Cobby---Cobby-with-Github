package com.cobby.main.title.db.entity;

import com.cobby.main.quest.db.entity.Quest;

import com.cobby.main.title.api.dto.response.TitleGetResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Title {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int titleId;

	@ManyToOne
	@JoinColumn(name = "quest_id")
	private Quest quest;

	private String name;

	private String explanation;

	// Entity to Dto
	public TitleGetResponse toDto() {return new TitleGetResponse(titleId, quest.getQuestId(), name, explanation);}

}
