package com.cobby.auth.api.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto {
    private String userId; // UUID
    private String nickname;
    private String githubUrl;
    private String githubToken;

    public UserInfoDto(String userId) {
        this.userId = userId;
    }

}
