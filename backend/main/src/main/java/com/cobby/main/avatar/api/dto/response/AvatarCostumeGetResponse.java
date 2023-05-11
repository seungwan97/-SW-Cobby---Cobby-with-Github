package com.cobby.main.avatar.api.dto.response;

import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;

import lombok.Builder;
import lombok.Data;

@Data
public class AvatarCostumeGetResponse {

	private Long costumeId;

	private String name;

	private CostumeCategory category;

	private Long questId;

	private String imgUrl;

	private String gifUrl;

	@Builder
	public AvatarCostumeGetResponse(Costume costume) {
		this.costumeId = costume.getCostumeId();
		this.name = costume.getName();
		this.category = costume.getCategory();
		this.questId = costume.getQuest().getQuestId();
		this.imgUrl = costume.getImgUrl();
		this.gifUrl = costume.getGifUrl();
	}
}
