package com.cobby.main.quest.api.controller;

import com.cobby.main.common.util.ApiDocumentResponse;
import com.cobby.main.quest.api.dto.request.QuestPostRequest;
import com.cobby.main.quest.api.dto.request.QuestPutRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.quest.api.service.QuestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "도전과제", description = "도전과제 관련 API 문서입니다.")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/quests")
public class QuestController {

	private final QuestService questService;

	@ApiDocumentResponse
	@Operation(summary = "도전과제 조회", description = "quest ID로 칭호를 조회하는 메서드 입니다.")
	@GetMapping("/one/{questId}")
	public ResponseEntity<? extends BaseResponseBody> getQuest(@PathVariable Integer questId) {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "OK", questService.selectQuest(questId)));
	}

	@ApiDocumentResponse
	@Operation(summary = "도전과제 목록 전체 조회", description = "모든 도전과제를 조회하는 메서드 입니다.")
	@GetMapping
	public ResponseEntity<? extends BaseResponseBody> getAllQuests() {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "OK", questService.selectAllQuest()));
	}

	@ApiDocumentResponse
	@Operation(summary = "도전과제 생성", description = "도전과제 이름, 종류, ")
	@PostMapping("/new")
	public ResponseEntity<? extends BaseResponseBody> createQuest(@RequestBody QuestPostRequest questInfo) {
		questService.insertQuest(questInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Created", "생성되었습니다."));
	}

	@ApiDocumentResponse
	@Operation(summary = "도전과제 목록 전체 조회", description = "모든 도전과제를 조회하는 메서드 입니다.")
	@PutMapping
	public ResponseEntity<? extends BaseResponseBody> updateQuest(@RequestBody QuestPutRequest questInfo) {
		questService.updateQuest(questInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Updated", "수정되었습니다."));
	}

	@ApiDocumentResponse
	@Operation(summary = "도전과제 목록 전체 조회", description = "모든 도전과제를 조회하는 메서드 입니다.")
	@DeleteMapping("/{questId}")
	public ResponseEntity<? extends BaseResponseBody> deleteQuest(@PathVariable Integer questId) {
		questService.deleteQuest(questId);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Deleted", "삭제되었습니다."));
	}

}
