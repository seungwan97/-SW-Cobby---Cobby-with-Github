package com.cobby.main.stat.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record StatPostRequest (

	@Schema(description = "총 커밋 횟수", example = "100")
	Long commitCnt,

	@Schema(description = "받은 스타 총 개수", example = "50")
	Long starCnt,

	@Schema(description = "풀리퀘스트 횟수", example = "40")
	Long prCnt,

	@Schema(description = "팔로워 수", example = "60")
	Long followerCnt,

	@Schema(description = "이슈 횟수", example = "20")
	Long issueCnt

){}
