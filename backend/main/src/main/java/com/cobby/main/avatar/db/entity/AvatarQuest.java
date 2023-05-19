package com.cobby.main.avatar.db.entity;

import com.cobby.main.common.entity.BaseTimeEntity;
import com.cobby.main.quest.db.entity.Quest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "avatar_quest")
@Entity
public class AvatarQuest extends BaseTimeEntity {

	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "avatar_quest_id", nullable = false, columnDefinition = "INT UNSIGNED")
	private Long avatarQuestId;

	@NotNull(message = "필수 입력 항목입니다. (avatar)")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;

	@NotNull(message = "필수 입력 항목입니다. (quest)")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quest_id")
	private Quest quest;

	@Builder
	public AvatarQuest(Avatar avatar, Quest quest) {
		this.avatar = avatar;
		this.quest = quest;
	}
}
