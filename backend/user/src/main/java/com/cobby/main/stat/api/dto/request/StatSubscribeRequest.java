package com.cobby.main.stat.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record StatSubscribeRequest(

	@Schema(description = "유저 ID", example = "abfsd-asdfasd-basfab")
	String userId,

	@Schema(description = "스탯 정보", example = "커밋, 스타, 이슈, pr, 팔로워 등")
	StatPostRequest statPostRequest
){}
