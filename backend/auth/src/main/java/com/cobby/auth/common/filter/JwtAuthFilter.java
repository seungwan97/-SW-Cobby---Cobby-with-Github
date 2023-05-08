package com.cobby.auth.common.filter;

import com.cobby.auth.api.customenum.JwtCode;
import com.cobby.auth.api.customenum.Role;
import com.cobby.auth.api.customenum.TokenKey;
import com.cobby.auth.api.dto.UserDto;
import com.cobby.auth.api.service.TokenProvider;
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

        if(token != null && tokenProvider.validateToken(token) ==  JwtCode.ACCESS) {
            String userId = tokenProvider.getUid(token);

            UserDto userDto = UserDto.builder()
                    .userId(userId)
                    .build();

            Authentication auth = getAuthentication(userDto);
            SecurityContextHolder.getContext().setAuthentication(auth);

            log.info("set Authentication to security context for '{}', uri = {}", auth.getName(), ((HttpServletRequest)request).getRequestURI());

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
