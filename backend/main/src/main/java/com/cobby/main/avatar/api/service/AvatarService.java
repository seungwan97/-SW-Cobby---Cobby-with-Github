package com.cobby.main.avatar.api.service;

import java.util.Map;

import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;

public interface AvatarService {

	AvatarGetResponse selectAvatar(String userId);

	String insertDefaultAvatar(String userId);

	String updateAvatar(String userId, Map<String, Integer> avatarUpdateInfo);

	String resetAvatar(String userId);

	String deleteAvatar(String userId);
}
