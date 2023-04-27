package com.cobby.main.title.api.dto.request;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitlePostRequest {

    private Integer questId;

    private String name;

    private String explanation;

}
