package com.cobby.main.title.api.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitlePostRequest {

    @Schema(description = "관련된 도전과제 ID", example = "1")
    private Integer questId;

    @Schema(description = "칭호 이름", example = "지옥에서 돌아온 자")
    private String name;

    @Schema(description = "칭호 설명", example = "지옥불 맛을 보고 돌아온 사람에게 수여되는 칭호")
    private String explanation;

}
