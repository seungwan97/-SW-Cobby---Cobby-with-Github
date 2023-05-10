package com.cobby.auth.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/{token}")
    boolean checkAccessToken(@PathVariable String token) {
        log.info("token = {}", token);
        return true;
    }

    @PostMapping
    boolean webhookTest() {
        log.info("webhook Test in");
        return true;
    }

    /**
     * Refresh Token
     * FE 에서 Access Token 확인 후, 만료 되었을 때 오는 Re:issue 요청
    **/
//    @PostMapping(value = "/reissue")
//    public ResponseEntity<?> reissue(HttpServletRequest httpServletRequest) {
//
//    }

}
