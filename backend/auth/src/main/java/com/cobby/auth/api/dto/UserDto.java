package com.cobby.auth.api.dto;

import com.cobby.auth.api.entity.User;
import lombok.*;

import java.util.UUID;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String oauthId;

    public User initUser(UserDto userDto) {
        return User.builder()
                .oauthId(userDto.getOauthId())
                .userId(UUID.randomUUID())
                .build();
    }

}
