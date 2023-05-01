package com.cobby.main.avatar.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;

public record AvatarUpdateRequest(
	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = 256, message = "범위를 벗어났습니다.")
	Integer level,

	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = 167025, message = "범위를 벗어났습니다.")
	Integer exp
) {
}
