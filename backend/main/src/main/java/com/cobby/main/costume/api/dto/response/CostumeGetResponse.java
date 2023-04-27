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

	private Quest quest;

	private String imgUrl;

	private String silhouetteImgUrl;

	private String gifUrl;

	@Builder
	public CostumeGetResponse(Costume costume) {
		this.costumeId = costume.getCostumeId();
		this.name = costume.getName();
		this.category = costume.getCategory();
		this.quest = costume.getQuest();
		this.imgUrl = costume.getImgUrl();
		this.silhouetteImgUrl = costume.getSilhouetteImgUrl();
		this.gifUrl = costume.getGifUrl();
	}

}
