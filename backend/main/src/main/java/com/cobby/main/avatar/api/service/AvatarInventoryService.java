package com.cobby.main.avatar.api.service;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;

public interface AvatarInventoryService {

	Integer insertAvatarInventoryItem(String userId, AvatarItemPostRequest itemInfo);
}
