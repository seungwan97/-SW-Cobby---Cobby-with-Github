package com.cobby.main.avatar.api.service;

import java.util.List;

import com.cobby.main.avatar.api.dto.response.AvatarCostumeGetResponse;

public interface AvatarCostumeService extends AvatarItemService<AvatarCostumeGetResponse> {
	List<AvatarCostumeGetResponse> selectAllCostumesByType(String userId, String category);
}
