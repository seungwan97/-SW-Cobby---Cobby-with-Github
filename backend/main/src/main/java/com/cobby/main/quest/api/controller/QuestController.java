package com.cobby.main.quest.api.controller;

import com.cobby.main.avatar.api.service.AvatarQuestService;
import com.cobby.main.common.util.ApiDocumentResponse;
import com.cobby.main.quest.api.dto.request.QuestPostRequest;
import com.cobby.main.quest.api.dto.request.QuestPutRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.quest.api.service.QuestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@Tag(name = "도전과제", description = "도전과제 관련 API 문서입니다.")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/quests")
public class QuestController {

	private final QuestService questService;

	private final AvatarQuestService avatarQuestService;

	@ApiDocumentResponse
	@Operation(summary = "도전과제 조회", description = "quest ID로 도전과제를 조회합니다.")
	@GetMapping("/one/{questId}")
	public ResponseEntity<? extends BaseResponseBody> getQuest(@PathVariable Long questId) {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "OK", questService.selectQuest(questId)));
	}

	@ApiDocumentResponse
	@Operation(summary = "도전과제 목록 전체 조회", description = "모든 도전과제를 조회합니다.")
	@GetMapping
	public ResponseEntity<? extends BaseResponseBody> getAllQuests() {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "OK", questService.selectAllQuest()));
	}

	@ApiDocumentResponse
	@Operation(summary = "도전과제 생성", description = "도전과제 이름, 종류, 달성 조건, 보상으로 이루어진 도전과제를 생성합니다.")
	@PostMapping("/new")
	public ResponseEntity<? extends BaseResponseBody> createQuest(@RequestBody QuestPostRequest questInfo) {
		questService.insertQuest(questInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Created", "생성되었습니다."));
	}

	@ApiDocumentResponse
	@Operation(summary = "도전과제 수정", description = "도전과제 ID에 해당하는 도전과제 이름, 종류, 달성 조건, 보상으로 이루어진 도전과제를 수정합니다.")
	@PutMapping
	public ResponseEntity<? extends BaseResponseBody> updateQuest(@RequestBody QuestPutRequest questInfo) {
		questService.updateQuest(questInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Updated", "수정되었습니다."));
	}

	@ApiDocumentResponse
	@Operation(summary = "도전과제 삭제", description = "도전과제 ID에 해당하는 도전과제를 삭제합니다.")
	@DeleteMapping("/{questId}")
	public ResponseEntity<? extends BaseResponseBody> deleteQuest(@PathVariable Long questId) {
		questService.deleteQuest(questId);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Deleted", "삭제되었습니다."));
	}

	@ApiDocumentResponse
	@Operation(summary = "아바타 도전과제 목록 조회", description = "아바타의 현재 도전과제 목록을 조회합니다.")
	@GetMapping(value = "/current")
	public ResponseEntity<? extends BaseResponseBody> getAvatarCurrentQuests(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId
	) {
		var currentQuests = questService.selectCurrentQuests(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "ok", currentQuests));
	}

}
