package com.cobby.main.stat.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StatResponse {

	private int commitCnt;
	private int starCnt;
	private int prCnt;
	private int followerCnt;
	private int issueCnt;

	@Builder
	public StatResponse(int commitCnt, int starCnt, int prCnt, int followerCnt, int issueCnt) {
		this.commitCnt = commitCnt;
		this.starCnt = starCnt;
		this.prCnt = prCnt;
		this.followerCnt = followerCnt;
		this.issueCnt = issueCnt;
	}

}
