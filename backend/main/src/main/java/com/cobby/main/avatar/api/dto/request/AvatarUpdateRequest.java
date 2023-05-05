package com.cobby.main.avatar.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;

public record AvatarUpdateRequest(
	@Schema(description = "아바타 레벨", example = "10")
	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = 256, message = "범위를 벗어났습니다.")
	Integer level,

	@Schema(description = "아바타 경험치", example = "1026")
	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = 167025, message = "범위를 벗어났습니다.")
	Integer exp
) {
}
