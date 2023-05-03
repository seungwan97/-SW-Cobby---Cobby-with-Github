package com.cobby.main.stat.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StatResponse {

	private int commitCnt;
	private int starCnt;
	private int forkCnt;
	private int prCnt;
	private int followerCnt;
	private int issueCnt;

}
