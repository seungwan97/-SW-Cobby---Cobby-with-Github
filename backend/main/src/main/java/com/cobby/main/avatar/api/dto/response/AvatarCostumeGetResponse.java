package com.cobby.main.avatar.api.dto.response;

import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.costume.db.entity.Costume;

import lombok.Builder;
import lombok.Data;

@Data
public class AvatarCostumeGetResponse {

	private Long avatarCostumeId;

	private CostumeGetResponse costume;

	@Builder
	public AvatarCostumeGetResponse(Long avatarCostumeId, Costume costume) {
		this.avatarCostumeId = avatarCostumeId;
		this.costume = CostumeGetResponse.builder()
			.costume(costume)
			.build();
	}
}
