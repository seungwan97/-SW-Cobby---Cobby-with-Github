package com.cobby.main.user.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.user.api.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@GetMapping("/{userId}") // 회원 정보 조회
	public ResponseEntity<? extends BaseResponseBody> getUserInfo(@PathVariable String userId) {

		var info = userService.getUserInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", info));
	}

	@PatchMapping("/{userId}") // 회원 정보 수정
	public ResponseEntity<? extends BaseResponseBody> updateUserInfo(@PathVariable String userId) {

		userService.updateUserInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "정보가 수정되었습니다."));
	}

	@PatchMapping("/signout/{userId}") // 회원 상태 수정
	public ResponseEntity<? extends BaseResponseBody> updateUserStatus(@PathVariable String userId) {

		userService.updateUserStatus(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "상태가 수정되었습니다."));
	}
}
