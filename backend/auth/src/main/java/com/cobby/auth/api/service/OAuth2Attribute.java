package com.cobby.auth.api.service;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class OAuth2Attribute {
    /*
    OAuth2 인증 후 보내주는 데이터가 각 인증 서버바다 다르기 때문에 별도의 분기 처리가 필요
    */

    private Map<String, Object> attributes;
    private String id;
    private String attributeKey;
    private String nickname;
    private String htmlUrl;

    static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {

        switch (provider) {
            case "github":
//                System.out.println("attributeKey : " + attributeKey);
//                1. attributeKey 가 "id" 인지 확인
                return ofGithub(attributeKey, attributes);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuth2Attribute ofGithub(String attributeKey, Map<String, Object> attributes) {

        return OAuth2Attribute.builder()
                .id(String.valueOf(attributes.get("id")))
                .nickname((String) attributes.get("login"))
                .htmlUrl((String) attributes.get("html_url"))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("key", attributeKey);
        map.put("nickname", nickname);
        map.put("html_url", htmlUrl);
        return map;
    }
}
