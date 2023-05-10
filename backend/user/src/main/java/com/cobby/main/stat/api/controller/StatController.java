package com.cobby.main.stat.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.stat.api.dto.request.StatSubscribeRequest;
import com.cobby.main.stat.api.service.StatService;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stat")
public class StatController {

	private final StatService statService;

	@GetMapping // 회원 스탯 정보 조회
	public ResponseEntity<? extends BaseResponseBody> getUserInfo(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var info = statService.getUserInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", info));
	}
	@PostMapping("/subscribe")
	public ResponseEntity<? extends BaseResponseBody> subscribeUserInfo(@RequestBody StatSubscribeRequest statSubscribeRequest){

		statService.subscribeUserInfo(statSubscribeRequest);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "수정되었습니다."));
	}

}
