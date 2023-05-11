package com.cobby.main.costume.api.dto.response;

import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.quest.db.entity.Quest;

import lombok.Builder;
import lombok.Data;

@Data
public class CostumeGetResponse {

	private Long costumeId;

	private String name;

	private CostumeCategory category;

	private Long questId;

	private String imgUrl;

	private String gifUrl;

	@Builder
	public CostumeGetResponse(Costume costume) {
		this.costumeId = costume.getCostumeId();
		this.name = costume.getName();
		this.category = costume.getCategory();
		this.questId = costume.getQuest().getQuestId();
		this.imgUrl = costume.getImgUrl();
		this.gifUrl = costume.getGifUrl();
	}

}
