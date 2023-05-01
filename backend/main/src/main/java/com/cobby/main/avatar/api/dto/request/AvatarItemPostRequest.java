package com.cobby.main.avatar.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AvatarItemPostRequest(
	@NotBlank(message = "필수 입력 항목입니다. (costume / title / quest)")
	String itemType,

	@Positive(message = "필수 입력 항목입니다. (양수)")
	Integer itemId
) {
}
