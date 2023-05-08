package com.cobby.auth.api.service;

import com.cobby.auth.api.customenum.JwtCode;
import com.cobby.auth.api.customenum.TokenKey;
import com.cobby.auth.api.dto.Token;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    private final String secret;
    private final long tokenValidityInMilliseconds;

    @Autowired
    public TokenProvider(@Value("${jwt.secret}") String secret,
        @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds
    ) {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
    }

    public String createToken(String userId, String role, TokenKey tokenKey) {
        // access : 1 hour, refresh : 1 month
        long period = getExpiration(tokenKey);

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", role);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + period))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public JwtCode validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return JwtCode.ACCESS;
        } catch (ExpiredJwtException e) {
            // 만료된 경우에는 refresh token을 확인하기 위해
            return JwtCode.EXPIRED;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("JwtException = {}", e);
        }
        return JwtCode.DENIED;
    }

    private long getExpiration(TokenKey tokenKey) {

        String delimiter = tokenKey.getKey();
        if(delimiter.equals(TokenKey.ACCESS.getKey())) {
            return tokenValidityInMilliseconds;
        } else if (delimiter.equals(TokenKey.REFRESH.getKey())) {
            return tokenValidityInMilliseconds * 2L * 24L * 30;
        } else {
            throw new RuntimeException();
        }
    }

    public String generateAccess(String userId, String role) {
        return createToken(userId, role, TokenKey.ACCESS);
    }

    public String generateRefresh(String userId, String role) {
        return createToken(userId, role, TokenKey.REFRESH);
    }

    public Token generateToken(String userId, String role) {
        String accessToken = generateAccess(userId, role);
        String refreshToken = generateRefresh(userId, role);

        return new Token(accessToken, refreshToken);
    }

    public String getUid(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    public String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer-")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
