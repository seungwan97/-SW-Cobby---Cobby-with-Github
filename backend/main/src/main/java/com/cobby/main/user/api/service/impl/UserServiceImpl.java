package com.cobby.main.user.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cobby.main.user.api.dto.response.UserGetResponse;
import com.cobby.main.user.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<UserGetResponse> getUserInfo(String userId) {
		return null;
	}

	@Override
	public void updateUserInfo(String userId) {

	}

	@Override
	public void updateUserStatus(String userId) {

	}
}
