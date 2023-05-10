package com.cobby.main.user.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserPostRequest (

	@Schema(description = "유저 ID", example = "asdf-asdfsa-asdfsa")
	String userId,

	@Schema(description = "유저 닉네임", example = "짱남")
	String nickname,

	@Schema(description = "깃헙 주소", example = "url")
	String githubUrl,

	@Schema(description = "깃헙 토큰", example = "토큰")
	String githubToken
){}
