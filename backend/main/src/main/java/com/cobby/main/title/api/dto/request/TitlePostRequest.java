package com.cobby.main.title.api.dto.request;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitlePostRequest {

    private int questId;

    private String name;

    private String explanation;

}
