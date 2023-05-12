package com.cobby.main.avatar.api.dto.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.costume.db.entity.Costume;

import lombok.Builder;
import lombok.Data;

@Data
public class AvatarGetResponse {
	private Integer level;

	private Integer exp;

	private Integer nextExp;

	private Map<String, CostumeGetResponse> outfits;

	private List<AvatarCostumeGetResponse> costumes;

	private List<AvatarTitleGetResponse> titles;

	private List<AvatarQuestGetResponse> quests;

	@Builder
	public AvatarGetResponse (Avatar avatar, Integer nextExp, Map<String, CostumeGetResponse> outfits) {
		this.level = avatar.getLevel();
		this.exp = avatar.getExp();
		this.nextExp = nextExp;
		this.outfits = outfits;
		this.costumes = avatar.getCostumes().stream()
			.map(avatarCostume -> AvatarCostumeGetResponse.builder()
				.costume(avatarCostume.getCostume())
				.build())
			.toList();
		this.titles = avatar.getTitles().stream()
			.map(avatarTitle -> AvatarTitleGetResponse.builder()
				.title(avatarTitle.getTitle())
				.build())
			.toList();
		this.quests = avatar.getQuests().stream()
			.map(avatarQuest -> AvatarQuestGetResponse.builder()
				.quest(avatarQuest.getQuest())
				.build())
			.toList();
	}
}
