package com.cobby.main.costume.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CostumePostRequest {

	@NotBlank(message = "필수 입력 항목입니다. (String)")
	private String name;

	@NotBlank(message = "필수 입력 항목입니다. (DEFAULT / HEAD / BODY / FACE)")
	private String category;

	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = Integer.MAX_VALUE, message = "범위를 벗어났습니다.")
	private Integer questId;
}
