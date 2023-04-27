package com.cobby.main.avatar.api.dto.response;

import java.util.List;

import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.avatar.db.entity.AvatarCostume;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
public class AvatarGetResponse {
	private Integer level;

	private Integer exp;

	private String characterImgUrl;

	private List<AvatarCostume> costumes;

	// private List<AvatarTitle> titles;

	// private List<AvatarQuest> quests;

	@Builder
	public AvatarGetResponse (Avatar avatar) {
		this.level = avatar.getLevel();
		this.exp = avatar.getExp();
		this.characterImgUrl = avatar.getAvatarImgUrl();
		this.costumes = avatar.getCostumes();
		// this.titles = avatar.getTitles();
		// this.quests = avatar.getQuests();
	}
}
