package com.cobby.main.user.api.service;

import java.util.List;

import com.cobby.main.user.api.dto.response.UserMainResponse;

public interface UserService {

	UserMainResponse getUserInfo(String userId);
	void signOutUserInfo(String userId);

}
