package com.cobby.main.costume.api.dto.request;

import org.springframework.util.Assert;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record CostumePostRequest (
	@NotBlank(message = "필수 입력 항목입니다. (String)")
	String name,

	@NotBlank(message = "필수 입력 항목입니다. (DEFAULT / HEAD / BODY / FACE)")
	String category,

	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = Integer.MAX_VALUE, message = "범위를 벗어났습니다.")
	Integer questId
){

}
