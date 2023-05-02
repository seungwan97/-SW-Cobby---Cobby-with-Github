package com.cobby.main.avatar.db.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "avatar")
// @Builder(toBuilder = true)
@Entity
public class Avatar {

	@Id
	@Column(name = "avatar_id", nullable = false)
	private String avatarId;

	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = 256, message = "범위를 벗어났습니다. ")
	@Column(name = "level", nullable = false, columnDefinition = "TINYINT UNSIGNED")
	private Integer level;

	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = 167025, message = "범위를 벗어났습니다. ")
	@Column(name = "exp", nullable = false, columnDefinition = "INT UNSIGNED")
	private Integer exp;

	@Column(name = "avatar_img_url")
	private String avatarImgUrl;

	// @Builder.Default
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL)
	private List<AvatarCostume> costumes = new ArrayList<>();

	// @Builder.Default
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL)
	private List<AvatarTitle> titles = new ArrayList<>();

	// @Builder.Default
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL)
	private List<AvatarQuest> quests = new ArrayList<>();

	@Builder(toBuilder = true)
	public Avatar(String avatarId, Integer level, Integer exp, String avatarImgUrl) {
		Assert.isTrue(Pattern.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", avatarId), "올바르지 않은 ID 양식입니다.");
		Assert.isTrue(1 <= level && level <= 256, "범위를 벗어났습니다. ");
		Assert.isTrue(0 <= exp && exp <= 167025, "범위를 벗어났습니다. ");
		Assert.hasText(avatarImgUrl, "필수 항목힙니다.");

		this.avatarId = avatarId;
		this.level = level;
		this.exp = exp;
		this.avatarImgUrl = avatarImgUrl;
	}
}
