package com.cobby.main.avatar.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AvatarItemPostRequest(
	@Schema(description = "아이템 종류 (costume / title / quest)", example = "costume")
	@NotBlank(message = "필수 입력 항목입니다. (costume / title / quest)")
	String itemType,

	@Schema(description = "아이템 ID", example = "1")
	@Positive(message = "필수 입력 항목입니다. (양수)")
	Long itemId
) {
}
