package com.cobby.main.user.api.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.common.util.ApiDocumentResponse;
import com.cobby.main.user.api.dto.request.UserPostRequest;
import com.cobby.main.user.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/users")
@Tag(name = "회원 정보", description = "회원 깃헙 정보 관련 API 문서입니다.")
public class UserController {

	private final UserService userService;

	@GetMapping("/info") // 회원 정보 조회
	@ApiDocumentResponse
	@Operation(summary = "닉네임 조회", description = "유저의 깃헙 닉네임을 조회합니다.")
	public ResponseEntity<? extends BaseResponseBody> getUserInfo(
		@RequestAttribute("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var info = userService.getUserInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", info));
	}

	@Hidden
	@PatchMapping("/signout") // 회원 탈퇴
	@ApiDocumentResponse
	@Operation(summary = "*회원 탈퇴", description = "미구현")
	public ResponseEntity<? extends BaseResponseBody> signOutUserInfo(
		@RequestAttribute("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		userService.signOutUserInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "탈퇴되었습니다."));
	}

	@Hidden
	@PostMapping // 로그인
	@ApiDocumentResponse
	@Operation(summary = "*유저정보 전달", description = "로그인 시 사용자의 정보를 인증서버로부터 전달받습니다.")
	public ResponseEntity<? extends BaseResponseBody> logInUserInfo(@RequestBody UserPostRequest userPostRequest) {

		userService.logInUserInfo(userPostRequest);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "로그인되었습니다."));
	}
}
