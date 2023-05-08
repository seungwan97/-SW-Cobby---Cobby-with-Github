package com.cobby.main.stat.api.dto.request;

public record StatPostRequest (

	Long commitCnt,
	Long starCnt,
	Long prCnt,
	Long followerCnt,
	Long issueCnt

){}
