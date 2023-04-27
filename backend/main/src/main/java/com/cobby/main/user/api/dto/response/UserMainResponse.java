package com.cobby.main.user.api.dto.response;

import java.util.List;

import com.cobby.main.stat.api.dto.response.StatResponse;
import com.cobby.main.user.db.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserMainResponse {

	private String nickname;
	private int commit_cnt;
	private int star_cnt;
	private int fork_cnt;

	@Builder
	public UserMainResponse(String nickname, int commit_cnt, int star_cnt, int fork_cnt) {
		this.nickname = nickname;
		this.commit_cnt = commit_cnt;
		this.star_cnt = star_cnt;
		this.fork_cnt = fork_cnt;
	}
}
