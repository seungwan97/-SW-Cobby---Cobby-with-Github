package com.cobby.main.stat.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.stat.api.dto.request.StatSubscribeRequest;
import com.cobby.main.stat.api.service.StatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stat")
public class StatController {

	private final StatService statService;

	@GetMapping("/{userId}") // 회원 스탯 정보 조회
	public ResponseEntity<? extends BaseResponseBody> getUserInfo(@PathVariable String userId) {

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
