package com.cobby.main.user.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserMainResponse {

	private String nickname;

	@Builder
	public UserMainResponse(String nickname) {
		this.nickname = nickname;
	}
}
