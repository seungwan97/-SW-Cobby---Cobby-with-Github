package com.cobby.main.avatar.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

public record AvatarCostumePatchRequest(
	@Schema(description = "코스튬 ID", example = "1")
	@Positive(message = "필수 입력 항목입니다. (양수)")
	Long costumeId
) {
}
