package com.cobby.main.avatar.db.entity;

import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.common.entity.BaseTimeEntity;
import com.cobby.main.costume.db.entity.Costume;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "avatar_costume")
@Entity
public class AvatarCostume extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "avatar_costume_id", nullable = false, columnDefinition = "INT UNSIGNED")
	private Integer characterCostumeId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "costume_id")
	private Costume costume;

	public AvatarCostume(Avatar avatar, Costume costume) {
		this.avatar = avatar;
		this.costume = costume;
	}
}
