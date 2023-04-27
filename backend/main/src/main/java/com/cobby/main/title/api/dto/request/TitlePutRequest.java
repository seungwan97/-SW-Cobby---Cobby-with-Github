package com.cobby.main.title.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitlePutRequest {

    private Integer titleId;

    private Integer questId;

    private String name;

    private String explanation;

}
