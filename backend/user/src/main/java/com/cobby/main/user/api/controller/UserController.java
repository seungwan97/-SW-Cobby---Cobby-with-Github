package com.cobby.main.user.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.user.api.dto.request.UserPostRequest;
import com.cobby.main.user.api.service.UserService;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@GetMapping // 회원 정보 조회
	public ResponseEntity<? extends BaseResponseBody> getUserInfo(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var info = userService.getUserInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", info));
	}

	@PatchMapping("/signout") // 회원 탈퇴
	public ResponseEntity<? extends BaseResponseBody> signOutUserInfo(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		userService.signOutUserInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "탈퇴되었습니다."));
	}

	@PostMapping // 로그인
	public ResponseEntity<? extends BaseResponseBody> logInUserInfo(@RequestBody UserPostRequest userPostRequest) {

		userService.logInUserInfo(userPostRequest);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "로그인되었습니다."));
	}
}
