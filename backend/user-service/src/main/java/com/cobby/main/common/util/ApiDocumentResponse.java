package com.cobby.main.common.util;

import static com.cobby.main.common.util.HttpStatusCodeConstants.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java
import java.lang.invoke.MethodHandles;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.common.exception.handler.GlobalExceptionHandler;
import com.cobby.main.common.exception.handler.ValidationExceptionHandler;
import com.sun.net.httpserver.Authenticator;

=======
import com.cobby.main.common.exception.NotFoundException;
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java
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
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java
		content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.class))),
=======
		content = @Content(schema = @Schema(implementation = NotFoundException.class))),
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java

	@ApiResponse(
		responseCode = BAD_REQUEST+"",
		description = "유효성 검증 실패",
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java
		content = @Content(schema = @Schema(implementation = ValidationExceptionHandler.class))),
=======
		content = @Content(schema = @Schema(implementation = NotFoundException.class))),
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java

	@ApiResponse(
		responseCode = METHOD_NOT_ALLOWED+"",
		description = "잘못된 Method 요청",
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java
		content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.class))),
=======
		content = @Content(schema = @Schema(implementation = NotFoundException.class))),
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java

	@ApiResponse(
		responseCode = UNAUTHORIZED+"",
		description = "인증 실패",
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java
		content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.class))),
=======
		content = @Content(schema = @Schema(implementation = NotFoundException.class))),
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java

	@ApiResponse(
		responseCode = FORBIDDEN+"",
		description = "인가 실패(권한 없음)",
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java
		content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.class))),
=======
		content = @Content(schema = @Schema(implementation = NotFoundException.class))),
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java

	@ApiResponse(
		responseCode = METHOD_NOT_ALLOWED+"",
		description = "데이터 등록 실패",
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java
		content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.class))),
=======
		content = @Content(schema = @Schema(implementation = NotFoundException.class))),
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/util/ApiDocumentResponse.java

})
public @interface ApiDocumentResponse {
}
