package com.cobby.main.avatar.api.dto.response;

import java.util.List;
import java.util.Map;

import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.avatar.db.entity.AvatarCostume;
import com.cobby.main.avatar.db.entity.AvatarQuest;
import com.cobby.main.avatar.db.entity.AvatarTitle;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
public class AvatarGetResponse {
	private Integer level;

	private Integer exp;

	private Integer nextExp;

	private Map<String, String> outfits;

	private List<AvatarCostume> costumes;

	private List<AvatarTitle> titles;

	private List<AvatarQuest> quests;

	@Builder
	public AvatarGetResponse (Avatar avatar, Integer nextExp, Map<String, String> outfits) {
		this.level = avatar.getLevel();
		this.exp = avatar.getExp();
		this.nextExp = nextExp;
		this.outfits = outfits;
		this.costumes = avatar.getCostumes();
		this.titles = avatar.getTitles();
		this.quests = avatar.getQuests();

	}
}
