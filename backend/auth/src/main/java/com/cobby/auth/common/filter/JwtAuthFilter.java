package com.cobby.auth.common.filter;

import com.cobby.auth.api.customenum.JwtCode;
import com.cobby.auth.api.customenum.Role;
import com.cobby.auth.api.customenum.TokenKey;
import com.cobby.auth.api.dto.Token;
import com.cobby.auth.api.dto.UserDto;
import com.cobby.auth.api.service.TokenProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = tokenProvider.resolveToken(((HttpServletRequest)request).getHeader(TokenKey.ACCESS.getKey()));

        log.info("token = {}", token);

        if (token != null && tokenProvider.validateToken(token) == JwtCode.ACCESS) { // Jwt Token 만료되지 않았을 때,
            String userId = tokenProvider.getUid(token);

            UserDto userDto = UserDto.builder()
                    .userId(userId)
                    .build();

            Authentication auth = getAuthentication(userDto);
            SecurityContextHolder.getContext().setAuthentication(auth);

            log.info("set Authentication to security context for '{}', uri = {}", auth.getName(), ((HttpServletRequest)request).getRequestURI());

        }
        else if (token != null && tokenProvider.validateToken(token) == JwtCode.EXPIRED) { // Jwt Token 이 만료되었을 때,
            Claims claims = tokenProvider.getClaims(token);

            // 토큰에 저장된 유저정보 추출
            UserDto userDto = UserDto.builder()
                    .userId(claims.getSubject())
                    .build();

            // 헤더에 존재하는 리프레시 토큰
            String refresh = tokenProvider.resolveToken(
                    ((HttpServletRequest)request).getHeader(TokenKey.REFRESH.getKey()));

            // 캐시에 존재하는 리프레시 토큰
            String savedRefresh = tokenProvider.getSavedRefresh(userDto.getUserId());
            
            // Refresh Token 을 확인해서 Access Token 재발급
            if (token != null && refresh.equals(savedRefresh) && tokenProvider.validateToken(refresh) == JwtCode.ACCESS) {
                Token tokens = tokenProvider.generateToken(userDto.getUserId(), Role.USER.getKey());

                tokenProvider.setSaveRefresh(userDto.getUserId(), tokens.getRefreshToken(), tokenProvider.getExpiration(TokenKey.REFRESH));
            }
        }
        else {
            log.info("no valid JWT token found, uri: {}", ((HttpServletRequest)request).getRequestURI());
        }
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(UserDto member) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority(Role.USER.getKey())));
    }
}
