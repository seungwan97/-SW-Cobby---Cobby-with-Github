package com.cobby.main.avatar.db.entity;

import com.cobby.main.common.entity.BaseTimeEntity;
import com.cobby.main.title.db.entity.Title;
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
@Table(name = "avatar_title")
@Entity
public class AvatarTitle extends BaseTimeEntity {

	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "avatar_title_id", nullable = false, columnDefinition = "INT UNSIGNED")
	private Long avatarTitleId;

	@NotNull(message = "필수 입력 항목입니다. (avatar)")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;

	@NotNull(message = "필수 입력 항목입니다. (title)")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "title_id")
	private Title title;

	@Builder
	public AvatarTitle(Avatar avatar, Title title) {
		this.avatar = avatar;
		this.title = title;
	}
}
