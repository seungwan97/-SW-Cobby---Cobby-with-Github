package com.cobby.main.user.api.dto.response;

import com.cobby.main.user.db.entity.State;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

	private String userID;
	private String nickname;
	private String github_url;
	private State state;

	@Builder
	public UserResponse(String userID, String nickname, String github_url, State state) {
		this.userID = userID;
		this.nickname = nickname;
		this.github_url = github_url;
		this.state = state;
	}
}
