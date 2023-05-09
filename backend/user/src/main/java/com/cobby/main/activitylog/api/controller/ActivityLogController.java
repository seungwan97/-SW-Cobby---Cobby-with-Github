package com.cobby.main.activitylog.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.activitylog.api.dto.request.ActivityLogPostRequest;
import com.cobby.main.activitylog.api.service.ActivityLogService;
import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.stat.api.dto.request.StatSubscribeRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activityLog")
public class ActivityLogController {

	private final ActivityLogService activityLogService;


	// webhook을 통해 받아오는 정보들
	@PostMapping("/webhooks")
	public ResponseEntity<? extends BaseResponseBody> webhookCreate(
		@RequestHeader Map<String, String> headers,
		@RequestBody String payload
	) {
		activityLogService.webhookCreate(headers, payload);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "감지되었습니다."));
	}

	@GetMapping("/{userId}") // 회원 연속 출석 조회
	public ResponseEntity<? extends BaseResponseBody> getactivityLogInfo(@PathVariable String userId) {

		var info = activityLogService.getactivityLogInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", info));
	}
}
