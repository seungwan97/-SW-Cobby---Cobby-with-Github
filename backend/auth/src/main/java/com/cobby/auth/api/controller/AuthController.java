package com.cobby.auth.api.controller;

import com.cobby.auth.common.model.BaseResponseBody;
import com.cobby.auth.common.util.ApiDocumentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Tag(name = "인증 정보", description = "OAuth2 인증 정보 API 문서입니다.")
public class AuthController {

    @PostMapping
    boolean webhookTest() {
        log.info("webhook Test in");
        return true;
    }

    @GetMapping("/data")
    @ApiDocumentResponse
    @Operation(summary = "토큰 조회", description = "Request Header 에 있는 Access Token 값을 반환하는 메서드 입니다.")
    public ResponseEntity<? extends BaseResponseBody> getTokenData(@RequestHeader("Authorization") String token) {

        return ResponseEntity
                .ok()
                .body(new BaseResponseBody<>(200, "Access Token fetched successfully", token));
    }

    /**
     * Refresh Token
     * FE 에서 Access Token 확인 후, 만료 되었을 때 오는 Re:issue 요청
    **/
    @GetMapping(value = "/reissue")
    @ApiDocumentResponse
    @Operation(summary = "토큰 조회", description = "Request Header 에 있는 쿠키의 Refresh Token 값을 반환하는 메서드 입니다.")
    public ResponseEntity<? extends BaseResponseBody> reissue(@RequestHeader("Cookie") String token) {

        return ResponseEntity
                .ok()
                .body(new BaseResponseBody<>(200, "Refresh Token fetched successfully", token));

    }

}
