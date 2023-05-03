package com.cobby.main.user.api.dto.response;

import java.util.List;

import com.cobby.main.stat.api.dto.response.StatResponse;
import com.cobby.main.user.db.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserMainResponse {

	private String nickname;
	private int commitCnt;
	private int starCnt;
	private int forkCnt;

	@Builder
	public UserMainResponse(String nickname, int commitCnt, int starCnt, int forkCnt) {
		this.nickname = nickname;
		this.commitCnt = commitCnt;
		this.starCnt = starCnt;
		this.forkCnt = forkCnt;
	}
}
