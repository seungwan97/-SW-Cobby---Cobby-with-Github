package com.cobby.main.avatar.api.service;

import com.cobby.main.avatar.api.dto.request.AvatarPatchRequest;
import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AvatarService {

	AvatarGetResponse selectAvatar(String avatarId) throws JsonProcessingException;

	String updateAvatar(String avatarId, AvatarPatchRequest avatarUpdateInfo) throws JsonProcessingException;

	String resetAvatar(String avatarId) throws JsonProcessingException;

	String deleteAvatar(String avatarId);
}
