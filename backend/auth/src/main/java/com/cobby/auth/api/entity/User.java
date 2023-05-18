package com.cobby.auth.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Document("user_info")
public class User {

    @Field
    private String oauthId;

    @Field
    private UUID userId;

}
