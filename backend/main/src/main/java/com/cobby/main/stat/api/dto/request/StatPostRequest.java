package com.cobby.main.stat.api.dto.request;

import lombok.Getter;

public record StatPostRequest (

	Long commitCnt,
	Long starCnt,
	Long forkCnt,
	Long prCnt,
	Long followerCnt,
	Long issueCnt

){}
