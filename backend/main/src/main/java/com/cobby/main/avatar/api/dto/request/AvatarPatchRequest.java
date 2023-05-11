package com.cobby.main.avatar.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record AvatarPatchRequest(
	@Schema(description = "수정하고자 하는 코스튬 ID 정보", minimum = "0")
	Long head,

	@Schema(description = "얼굴 부분 코스튬 ID", minimum = "0")
	Long face,

	@Schema(description = "몸통 부분 코스튬 ID", minimum = "0")
	Long body
) {
}
