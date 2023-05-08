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

	@Builder
	public StatResponse(int commitCnt, int starCnt, int forkCnt, int prCnt, int followerCnt, int issueCnt) {
		this.commitCnt = commitCnt;
		this.starCnt = starCnt;
		this.forkCnt = forkCnt;
		this.prCnt = prCnt;
		this.followerCnt = followerCnt;
		this.issueCnt = issueCnt;
	}

	@Builder
	public StatResponse(int commitCnt, int starCnt, int forkCnt) {
		this.commitCnt = commitCnt;
		this.starCnt = starCnt;
		this.forkCnt = forkCnt;
	}
}
