package com.cobby.main.user.api.dto.response;

import java.util.List;

import com.cobby.main.stat.api.dto.response.StatResponse;
import com.cobby.main.user.db.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserMainResponse {

	private String nickname;

	@Builder
	public UserMainResponse(String nickname) {
		this.nickname = nickname;
	}
}
