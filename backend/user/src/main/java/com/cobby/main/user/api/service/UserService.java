package com.cobby.main.user.api.service;

import com.cobby.main.user.api.dto.request.UserPostRequest;
import com.cobby.main.user.api.dto.response.UserMainResponse;

public interface UserService {

	UserMainResponse getUserInfo(String userId);
	void signOutUserInfo(String userId);

	void logInUserInfo(UserPostRequest userPostRequest);
}
