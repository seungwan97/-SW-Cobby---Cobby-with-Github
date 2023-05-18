package com.cobby.main.user.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserMainResponse {

	private String nickname;
	private String githubUrl;

}
