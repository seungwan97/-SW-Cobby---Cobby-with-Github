package com.cobby.store.asset.api.dto.response;

import org.springframework.core.io.Resource;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AssetGetResponse(
	@NotBlank(message = "필수 항목입니다.")
	String contentType,

	@NotBlank(message = "필수 항목입니다.")
	Resource resource
) {
}
