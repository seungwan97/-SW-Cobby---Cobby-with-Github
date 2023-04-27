package com.cobby.main.costume.api.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.costume.api.dto.request.CostumePostRequest;
import com.cobby.main.costume.api.service.CostumeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("api/costumes")
public class CostumeController {
	private final CostumeService costumeService;

	@GetMapping
	public ResponseEntity<? extends BaseResponseBody> getAllCostumes() {
		var costumes = costumeService.selectAllCostumes();

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", costumes));
	}

	@GetMapping("/{costumeId}")
	public ResponseEntity<? extends BaseResponseBody> getCostume(
		@PathVariable
		@Positive(message = "올바르지 않은 ID 양식입니다.") // 최대 상한 조건(Max)도 추가를 고려해야 함
		Integer costumeId) {

		var costume = costumeService.selectCostume(costumeId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", costume));
	}

	@PostMapping
	public ResponseEntity<? extends BaseResponseBody> createCostume(
		@RequestBody @Valid CostumePostRequest costumePostRequest,
		HttpServletRequest request) {

		var costumeId = costumeService.insertCostume(costumePostRequest);

		var location = URI.create(request.getRequestURI() + "/" + costumeId);

		var successMessage = "코스튬 정보를 성공적으로 저장하였습니다. (ID=" + costumeId + ")";

		return ResponseEntity
			.created(location)
			.body(new BaseResponseBody<>(201, "created", successMessage));
	}

	@DeleteMapping
	public ResponseEntity<? extends BaseResponseBody> deleteCostume(
		@PathVariable
		@Positive(message = "올바르지 않은 ID 양식입니다.") // 최대 상한 조건(Max)도 추가를 고려해야 함
		Integer costumeId) {

		var deletedId = costumeService.deleteCostume(costumeId);

		var successMessage = "코스튬 정보를 성공적으로 삭제하였습니다. (ID=" + deletedId + ")";

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", successMessage));
	}
}
