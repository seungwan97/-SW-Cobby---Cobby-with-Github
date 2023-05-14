package com.cobby.auth.api.service;

import com.cobby.auth.api.client.UserProfileClient;
import com.cobby.auth.api.customenum.Role;
import com.cobby.auth.api.customenum.TokenKey;
import com.cobby.auth.api.dto.Token;
import com.cobby.auth.api.dto.UserDto;
import com.cobby.auth.api.dto.UserInfoDto;
import com.cobby.auth.api.entity.User;
import com.cobby.auth.common.exception.NotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final MongoTemplate mongoTemplate;
    private final TokenProvider tokenProvider;
    private final UserProfileClient userProfileClient;

    @Value("{request.url.front-login}")
    private String REDIRECT_URI;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        log.info("깃헙 토큰띠 = {}", attributes.get("githubToken"));
        log.info("깃헙 URL띠 = {}", attributes.get("githubUrl"));

        UserDto userDto = UserDto.builder()
                .oauthId(String.valueOf(attributes.get("id")))
                .build();

        UserInfoDto userInfoDto;

        User guest = new User();

        Token tokens = new Token();

        String init = "";

        // 1. ( Git token ) 을 기준으로 회원 정보를 받아온다.
        User user = Optional.ofNullable(mongoTemplate.findOne(new Query(Criteria.where("oauthId").is(userDto.getOauthId())),
                User.class,"user_info")).orElse(guest);

        // 2. 최초 로그인일때, 회원가입( UUID 생성 )을 진행한다.
        if (user.equals(guest)) {
            System.out.println("===================================================================== 최초 로그인");
            
            // 2-1. 회원 정보 저장 ( userDto 의 oauthId 에 해당하는 userId 생성  )
            mongoTemplate.save(userDto.initUser(userDto));

            // 2-2. oauthId 와 일치하는 user 의 userId 가 담긴 user 객체 불러와 저장 
            user = Optional.ofNullable(mongoTemplate.findOne(new Query(Criteria.where("oauthId").is(userDto.getOauthId())),
                    User.class,"user_info")).orElseThrow(NotFoundException::new);

            // 2-3. 전달할 회원 정보 저장
            userInfoDto = new UserInfoDto(
                    String.valueOf(user.getUserId()), (String) attributes.get("nickname"), (String) attributes.get("githubUrl"), (String) attributes.get("githubToken")
            );

            log.info("UserInfoDto Id check = {}", userInfoDto.getUserId());
            log.info("UserInfoDto Nick check = {}", userInfoDto.getNickname());
            log.info("UserInfoDto Git Token check = {}", userInfoDto.getGithubToken());
            log.info("UserInfoDto Git Url check = {}", userInfoDto.getGithubUrl());

            // 2-4. 토큰 발행
            tokens = tokenProvider.generateToken(userInfoDto.getUserId(), Role.USER.getKey());

            // 최초 로그인 일때, true < 헤더에 저장 >
            init = "true";
            response.setHeader("Initial", init);

        }
        else {
            System.out.println("===================================================================== 기존 회원 로그인");
            userInfoDto = new UserInfoDto(
                    String.valueOf(user.getUserId()), (String) attributes.get("nickname"), (String) attributes.get("githubUrl"), (String) attributes.get("githubToken")
            );

            log.info("UserInfoDto Id check = {}", userInfoDto.getUserId());
            log.info("UserInfoDto Nick check = {}", userInfoDto.getNickname());
            log.info("UserInfoDto Git Token check = {}", userInfoDto.getGithubToken());
            log.info("UserInfoDto Git Url check = {}", userInfoDto.getGithubUrl());

            String access = tokenProvider.generateAccess(userInfoDto.getUserId(), Role.USER.getKey());

            tokens = tokenProvider.generateToken(userInfoDto.getUserId(), Role.USER.getKey());

            // 기존 로그인 일때, false < 헤더에 저장 >
            init = "false";
            response.setHeader("Initial", init);

        }

        // 2-5. 프로필 DB에 저장
        var userinfo = userProfileClient.logInUserInfo(userInfoDto);
        log.info("UserInfo Body = {}", userinfo.getBody());
        log.info("UserInfo Status = {}", userinfo.getStatusCode());

        // 3. FE 에 요청할 targetUrl 생성
        String targetUrl;
        targetUrl = UriComponentsBuilder.fromUriString(REDIRECT_URI)
                .queryParam("Initial", init)
                .queryParam(TokenKey.ACCESS.getKey(), "Bearer-" + tokens.getAccessToken()) // access 토큰
                .build().toUriString();

        // 3-1. 쿠키 생성 및 Refresh Token 저장
        Cookie cookie = new Cookie("refreshToken", tokens.getRefreshToken());
        cookie.setPath("/"); // Cookie의 유효 경로 설정 (루트 경로로 설정하면 전체 사이트에서 접근 가능)
        cookie.setMaxAge(30 * 24 * 60 * 60); // Cookie의 유효 기간 설정 (예: 30일)
        cookie.setHttpOnly(true); // JavaScript에서 접근할 수 없도록 설정
        cookie.setSecure(true); // HTTPS에서만 전송하도록 설정 (필요한 경우)

        response.addCookie(cookie);

        // 3-2. 헤더에 access token 저장
        response.setHeader(TokenKey.ACCESS.getKey(), "Bearer " + tokens.getAccessToken());

        // 3-3. Front Page로 리다이렉트 수행
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }
}
