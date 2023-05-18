package com.cobby.main.common.authorization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthorizationFilter customAuthorizationFilter;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/favicon.ico",
                        "/error",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/v3/api-docs",
                        "/webjars/**"
                )
                .and()
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());    // 정적인 리소스들에 대해서 시큐리티 적용 무시.
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/authorization/SecurityConfig.java
                .requestMatchers("/api/main/health").permitAll()
                .requestMatchers("/api/main/swagger-ui/**").permitAll()
                .requestMatchers("/api/main/api-docs/**").permitAll()
                .requestMatchers("/api/main/swagger/**").permitAll()
                .requestMatchers("/api/main/titles/**").permitAll()
                .requestMatchers("/api/main/costumes/**").permitAll()
                .requestMatchers("/api/main/avatars/server/**").permitAll()
=======
                .requestMatchers("/api/user/health").permitAll()
                .requestMatchers("/api/user/users").permitAll()
                .requestMatchers("/api/user/activityLog/*/server").permitAll()
                .requestMatchers("/api/user/swagger-ui/**").permitAll()
                .requestMatchers("/api/user/api-docs/**").permitAll()
                .requestMatchers("/api/user/swagger/**").permitAll()
                .requestMatchers("/api/user/badge/**").permitAll()
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/authorization/SecurityConfig.java
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
