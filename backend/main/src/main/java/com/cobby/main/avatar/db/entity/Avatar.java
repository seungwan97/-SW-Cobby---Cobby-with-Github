package com.cobby.main.avatar.db.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder(toBuilder = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "avatar")
@Entity
public class Avatar {
	@Id
	@Column(name = "avatar_id", nullable = false)
	private String avatarId;

	@Column(name = "level", nullable = false, columnDefinition = "TINYINT UNSIGNED")
	private Integer level;

	@Column(name = "exp", nullable = false, columnDefinition = "INT UNSIGNED")
	private Integer exp;

	@Column(name = "avatar_img_url", nullable = true)
	private String avatarImgUrl;

	// @Column(name = "current_avatar_gif_url_list", nullable = false)
	// private String currentAvatarGifUrlList;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL)
	private List<AvatarCostume> costumes;

	// @JsonProperty(access = JsonProperty.Access.READ_WRITE)
	// @OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL)
	// private List<AvatarTitle> titles;
	//
	// @JsonProperty(access = JsonProperty.Access.READ_WRITE)
	// @OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL)
	// private List<AvatarQuest> quests;
}
