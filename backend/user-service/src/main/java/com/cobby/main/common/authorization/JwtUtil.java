package com.cobby.main.common.authorization;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String getUid(String token) {
        log.info("Jwt Util 생성 getUid() 실행");

        var encodeSecret = Base64.getEncoder().encodeToString(secret.getBytes());
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/authorization/JwtUtil.java

        log.info("3. secret Key Base64 인코딩 = {}", encodeSecret);
=======
        log.info("secret Key Base64 인코딩");
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/authorization/JwtUtil.java

        return Jwts.parserBuilder().setSigningKey(encodeSecret).build().parseClaimsJws(token).getBody().getSubject();
    }
}
