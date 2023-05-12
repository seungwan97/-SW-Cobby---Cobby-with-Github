package com.cobby.auth.common.util;

import static com.cobby.auth.common.util.HttpStatusCodeConstants.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cobby.auth.common.exception.NotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ApiResponses(value = {

        @ApiResponse(responseCode = OK+"", description = "API 호출 성공"),

        @ApiResponse(
                responseCode = NOT_FOUND+"",
                description = "존재하지 않는 API",
                content = @Content(schema = @Schema(implementation = NotFoundException.class))),

        @ApiResponse(
                responseCode = BAD_REQUEST+"",
                description = "유효성 검증 실패",
                content = @Content(schema = @Schema(implementation = NotFoundException.class))),

        @ApiResponse(
                responseCode = METHOD_NOT_ALLOWED+"",
                description = "잘못된 Method 요청",
                content = @Content(schema = @Schema(implementation = NotFoundException.class))),

        @ApiResponse(
                responseCode = UNAUTHORIZED+"",
                description = "인증 실패",
                content = @Content(schema = @Schema(implementation = NotFoundException.class))),

        @ApiResponse(
                responseCode = FORBIDDEN+"",
                description = "인가 실패(권한 없음)",
                content = @Content(schema = @Schema(implementation = NotFoundException.class))),

        @ApiResponse(
                responseCode = METHOD_NOT_ALLOWED+"",
                description = "데이터 등록 실패",
                content = @Content(schema = @Schema(implementation = NotFoundException.class))),

})
public @interface ApiDocumentResponse {
}
