package com.cobby.main.avatar.api.service;

import java.util.Map;

import com.cobby.main.avatar.api.dto.request.AvatarUpdateRequest;
import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AvatarService {

	AvatarGetResponse selectAvatar(String avatarId) throws JsonProcessingException;

	String insertDefaultAvatar(String avatarId);

	String updateAvatar(String avatarId, AvatarUpdateRequest avatarUpdateInfo) throws JsonProcessingException;

	String resetAvatar(String avatarId);

	String deleteAvatar(String avatarId);
}
