package com.cobby.main.user.api.service;

import java.util.List;

import com.cobby.main.user.api.dto.response.UserGetResponse;

public interface UserService {

	List<UserGetResponse> getUserInfo(String userId);
	void updateUserInfo(String userId);
	void updateUserStatus(String userId);

}
