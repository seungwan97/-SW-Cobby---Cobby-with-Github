package com.cobby.main.costume.api.dto.request;

import org.springframework.util.Assert;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record CostumePostRequest (
	@Schema(description = "코스튬 이름", example = "붉은악마 티셔츠")
	@NotBlank(message = "필수 입력 항목입니다. (String)")
	String name,

	@Schema(description = "코스튬 종류 (DEFAULT / HEAD / BODY / FACE)", example = "BODY")
	@NotBlank(message = "필수 입력 항목입니다. (DEFAULT / HEAD / BODY / FACE)")
	String category,

	@Schema(description = "관련된 도전과제 ID", example = "1")
	@Positive(message = "필수 입력 항목입니다. (양수)")
	@Max(value = Integer.MAX_VALUE, message = "범위를 벗어났습니다.")
	Long questId
){

}
