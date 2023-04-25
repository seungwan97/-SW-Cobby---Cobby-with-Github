package com.cobby.main.avatar.db.entity;

import java.util.List;

import com.cobby.main.costume.db.entity.AvatarCostume;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "avatar")
@Entity
public class Avatar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "avatar_id", nullable = false, columnDefinition="INT UNSIGNED")
	private Integer characterId;

	@Column(name="level", nullable = false, columnDefinition = "TINYINT UNSIGNED")
	private Integer level;

	@Column(name="exp", nullable = false, columnDefinition="INT UNSIGNED")
	private Integer exp;

	@Column(name="avatar_img_url", nullable = true)
	private String characterImgUrl;

	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL)
	private List<AvatarCostume> costumes;
}
