package com.cobby.main.avatar.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record AvatarUpdateRequest(
	@Schema(description = "아바타 레벨", example = "10")
	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = 256, message = "범위를 벗어났습니다.")
	Integer level,

	@Schema(description = "아바타 경험치 증가량", example = "10")
	@PositiveOrZero(message = "필수 입력 항목입니다. (양수)")
	@Max(value = 167025, message = "범위를 벗어났습니다.")
	Integer exp,

	@Schema(description = "수정하고자 하는 코스튬 ID 정보", example = "0")
	Long head,

	@Schema(description = "얼굴 부분 코스튬 ID", example = "0")
	Long face,

	@Schema(description = "몸통 부분 코스튬 ID", example = "0")
	Long body
) {
}
