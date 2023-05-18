package com.cobby.main.avatar.db.entity;

import org.hibernate.annotations.ColumnDefault;

import com.cobby.main.common.entity.BaseTimeEntity;
import com.cobby.main.costume.db.entity.Costume;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "avatar_costume")
@Entity
public class AvatarCostume extends BaseTimeEntity {

	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "avatar_costume_id", nullable = false, columnDefinition = "INT UNSIGNED")
	private Long avatarCostumeId;

	@NotNull(message = "필수 입력 항목입니다. (avatar)")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;

	@NotNull(message = "필수 입력 항목입니다. (costume)")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "costume_id")
	private Costume costume;

	@ColumnDefault("false")
	@Column(name = "is_opened", nullable = false, columnDefinition = "Boolean")
	private Boolean isOpened;

	@Builder(toBuilder = true)
	public AvatarCostume(Avatar avatar, Costume costume, Boolean isOpened) {
		this.avatar = avatar;
		this.costume = costume;
		this.isOpened = isOpened;
	}

	public void setOpened(Boolean opened) {
		isOpened = opened;
	}
}
