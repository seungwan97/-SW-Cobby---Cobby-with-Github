package com.cobby.main.avatar.api.service;

import java.util.List;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;

public interface AvatarItemService<DTO> {

	Long insertItem(String userId, AvatarItemPostRequest itemInfo);

	List<DTO> selectAllItems(String userId);

	DTO selectItem(String userId, Long itemId);
}
