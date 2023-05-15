package com.cobby.auth.config;

import com.cobby.auth.api.service.CustomOAuth2UserService;
import com.cobby.auth.api.service.OAuth2SuccessHandler;
import com.cobby.auth.api.service.TokenProvider;
import com.cobby.auth.common.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final TokenProvider tokenProvider;

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
                .ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());    // 정적인 리소스들에 대해서 시큐리티 적용 무시.
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(new JwtAuthFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        http.cors() // cors 설정
                .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                // REST 통신 방식이므로 csrf, form 로그인, 세션 제외
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Token 검증하는 페이지와 메인페이지는 인가 허가하며, 그 외엔 모두 인가가 필요
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/user/swagger").permitAll()
                .requestMatchers("/api/main/swagger").permitAll()
                .requestMatchers("/api/store/swagger").permitAll()
                .requestMatchers("/").permitAll()
                .and()
                // 로그인 인증 로직 수행
                .oauth2Login()
                .successHandler(successHandler)
                .userInfoEndpoint()
                .userService(oAuth2UserService);

        // 사용할 레포지토리 엔드포인트 -> 서비스 성공실패핸들러 베이스uri <<
        return http.build();
    }
}
