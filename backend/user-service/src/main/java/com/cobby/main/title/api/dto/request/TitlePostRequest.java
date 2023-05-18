package com.cobby.main.title.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record TitlePostRequest(
    @Schema(description = "관련된 도전과제 ID", example = "1")
    Long questId,

    @Schema(description = "칭호 이름", example = "핫이슈")
    String name,

    @Schema(description = "칭호 설명", example = "제시된 이슈 총 합계 100개 이상")
    String explanation
) {

}