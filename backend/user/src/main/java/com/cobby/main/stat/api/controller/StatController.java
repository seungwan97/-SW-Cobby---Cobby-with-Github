package com.cobby.main.stat.api.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.common.util.ApiDocumentResponse;
import com.cobby.main.stat.api.dto.request.StatSubscribeRequest;
import com.cobby.main.stat.api.service.StatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/stat")
@Tag(name = "회원 깃헙 정보", description = "회원 깃헙 정보 관련 API 문서입니다.")
public class StatController {

	private final StatService statService;

	@GetMapping // 회원 스탯 정보 조회
	@ApiDocumentResponse
	@Operation(summary = "회원 깃헙 정보 조회", description = "사용자의 깃헙 정보를 조회합니다.")
	public ResponseEntity<? extends BaseResponseBody> getStatInfo(
		@RequestAttribute("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var info = statService.getStatInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", info));
	}

	@Hidden
	@ApiDocumentResponse
	@PostMapping("/subscribe")
	@Operation(summary = "*무얼까요?", description = "대은이도 몰라요")
	public ResponseEntity<? extends BaseResponseBody> subscribeStatInfo(@RequestBody StatSubscribeRequest statSubscribeRequest){

		statService.subscribeStatInfo(statSubscribeRequest);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "수정되었습니다."));
	}

}
