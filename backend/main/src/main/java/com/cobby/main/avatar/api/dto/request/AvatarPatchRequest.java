package com.cobby.main.avatar.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record AvatarPatchRequest(
	@Schema(description = "머리 코스튬 ID", minimum = "0")
	Long head,

	@Schema(description = "몸통 코스튬 ID", minimum = "0")
	Long body,

	@Schema(description = "특수효과 코스튬 ID", minimum = "0")
	Long effect
) {
}
