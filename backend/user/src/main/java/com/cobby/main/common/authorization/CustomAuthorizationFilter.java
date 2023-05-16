package com.cobby.main.common.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("필터 진입");

        log.info("ㄴ> request uri : " + request.getRequestURI());
        if (request.getServletPath().contains("health")
                || request.getServletPath().equals("/api/user/users")
                || request.getServletPath().contains("swagger")
                || request.getServletPath().contains("webhooks")
                || request.getServletPath().contains("api-docs")
                || request.getServletPath().contains("/badge/")
        ) {    // 인증없이 건너 뛸 요청 설정
            filterChain.doFilter(request, response);
        } else {

        String token = request.getHeader("Authorization").substring(7);   // 헤더의 토큰 파싱 (Bearer 제거)

        log.info("토큰 -> {}", token);
        try {
            String userId = jwtUtil.getUid(token);

            log.info("획득한 userId : " + userId);
            addAuthorizationHeaders(request, userId);

            Authentication auth = getAuthentication(userId);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("code", HttpStatus.UNAUTHORIZED.value());
            body.put("error", e.getMessage());

            new ObjectMapper().writeValue(response.getOutputStream(), body);
        }

        filterChain.doFilter(request, response);

        }

    }

    // 성공적으로 검증이 되었기 때문에 인증된 헤더로 요청을 변경해준다. 서비스는 해당 헤더에서 userId(토큰)를 가져와 사용한다.
    private void addAuthorizationHeaders(HttpServletRequest request, String userId) {
        request.setAttribute("userId", userId);
    }

    public Authentication getAuthentication(String member) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
