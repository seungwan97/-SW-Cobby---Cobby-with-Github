package com.cobby.main.title.api.controller;

import com.cobby.main.common.util.ApiDocumentResponse;
import com.cobby.main.title.api.dto.request.TitlePostRequest;
import com.cobby.main.title.api.dto.request.TitlePutRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.title.api.dto.response.TitleGetResponse;
import com.cobby.main.title.api.service.TitleService;

import lombok.RequiredArgsConstructor;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/titles")
@Tag(name = "칭호", description = "칭호 관련 API 문서입니다.")
public class TitleController {

	private final TitleService titleService;

	@ApiDocumentResponse
	@Operation(summary = "칭호 목록 전체 조회", description = "모든 칭호를 조회합니다.")
	@GetMapping
	public ResponseEntity<? extends BaseResponseBody> getAllTitles() {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "OK", titleService.selectAllTitles()));
	}

	@ApiDocumentResponse
	@Operation(summary = "칭호 조회", description = "title ID로 칭호를 조회합니다.")
	@GetMapping("/{titleId}")
	public ResponseEntity<? extends BaseResponseBody> getTitle(
		@Parameter(description = "조회할 칭호 ID", required = true)
		@PathVariable Long titleId) {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "OK", titleService.selectTitle(titleId)));
	}

	@ApiDocumentResponse
	@Operation(summary = "칭호 생성", description = "칭호 이름, 설명, 보상이 되는 도전과제 ID로 생성합니다.")
	@PostMapping
	public ResponseEntity<? extends BaseResponseBody> createTitle(@RequestBody TitlePostRequest titleInfo) {
		titleService.insertTitle(titleInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Created", "생성되었습니다."));
	}

	@ApiDocumentResponse
	@Operation(summary = "칭호 수정", description = "칭호 ID에 해당하는 칭호의 이름, 설명, 보상이 되는 도전과제 ID를 수정합니다.")
	@PutMapping
	public ResponseEntity<? extends BaseResponseBody> updateTitle(@RequestBody TitlePutRequest titleInfo) {
		titleService.updateTitle(titleInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Updated", "수정되었습니다."));
	}

	@ApiDocumentResponse
	@Operation(summary = "칭호 삭제", description = "칭호 ID에 해당하는 칭호를 삭제합니다.")
	@DeleteMapping("/{titleId}")
	public ResponseEntity<? extends BaseResponseBody> deleteTitle(
		@Parameter(description = "조회할 칭호 ID", required = true)
		@PathVariable Long titleId) {
		titleService.deleteTitle(titleId);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Deleted", "삭제되었습니다."));
	}

}
