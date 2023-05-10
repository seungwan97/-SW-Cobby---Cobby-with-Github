package com.cobby.main.avatar.api.dto.response;

import com.cobby.main.title.api.dto.response.TitleGetResponse;
import com.cobby.main.title.db.entity.Title;

import lombok.Builder;
import lombok.Data;

@Data
public class AvatarTitleGetResponse {
	private Long avatarTitleId;

	private TitleGetResponse title;

	@Builder
	public AvatarTitleGetResponse(Long avatarTitleId, Title title) {
		this.avatarTitleId = avatarTitleId;
		this.title = TitleGetResponse.builder()
			.title(title)
			.build();
	}
}
