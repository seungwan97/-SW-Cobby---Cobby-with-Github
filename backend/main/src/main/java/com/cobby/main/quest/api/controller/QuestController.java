package com.cobby.main.quest.api.controller;

import java.net.URI;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.service.AvatarQuestService;
import com.cobby.main.common.util.ApiDocumentResponse;
import com.cobby.main.quest.api.dto.request.QuestPostRequest;
import com.cobby.main.quest.api.dto.request.QuestPutRequest;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.quest.api.service.QuestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/main/quests")
@Tag(name = "도전과제", description = "도전과제 관련 API 문서입니다.")
public class QuestController {

	private final QuestService questService;

	private final AvatarQuestService avatarQuestService;

	@Hidden
	@ApiDocumentResponse
	@Operation(summary = "#####도전과제 조회#####", description = "quest ID로 도전과제를 조회합니다.")
	@GetMapping("/one/{questId}")
	public ResponseEntity<? extends BaseResponseBody> getQuest(@PathVariable Long questId) {

		var quest = questService.selectQuest(questId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", quest));
	}

	@Hidden
	@ApiDocumentResponse
	@Operation(summary = "#####도전과제 목록 전체 조회#####", description = "모든 도전과제를 조회합니다.")
	@GetMapping
	public ResponseEntity<? extends BaseResponseBody> getAllQuests() {

		var quests = questService.selectAllQuest();

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", quests));
	}

	@Hidden
	@ApiDocumentResponse
	@Operation(summary = "#####도전과제 생성#####", description = "도전과제 이름, 종류, 달성 조건, 보상으로 이루어진 도전과제를 생성합니다.")
	@PostMapping("/new")
	public ResponseEntity<? extends BaseResponseBody> createQuest(
		@RequestBody @Valid QuestPostRequest questInfo,
		HttpServletRequest request
	) {
		var id = questService.insertQuest(questInfo);

		var location = URI.create(request.getRequestURI() + "/" + id);

		var successMessage = "아바타를 성공적으로 삭제했습니다. (ID=" + id + ")";

		return ResponseEntity
			.created(location)
			.body(new BaseResponseBody<>(201, "Created", successMessage));
	}

	@Hidden
	@ApiDocumentResponse
	@Operation(summary = "#####도전과제 삭제#####", description = "도전과제 ID에 해당하는 도전과제를 삭제합니다.")
	@DeleteMapping("/{questId}")
	public ResponseEntity<? extends BaseResponseBody> deleteQuest(@PathVariable Long questId) {
		var id = questService.deleteQuest(questId);

		var successMessage = "아바타를 성공적으로 삭제했습니다. (ID=" + id + ")";

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", successMessage));
	}

	@ApiDocumentResponse
	@Operation(summary = "아바타 도전과제 목록 조회", description = "아바타의 현재 도전과제 목록을 조회합니다.")
	@GetMapping(value = "/current")
	public ResponseEntity<? extends BaseResponseBody> getAvatarCurrentQuests(
		@RequestAttribute("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId
	) {
		var currentQuests = questService.selectCurrentQuests(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "ok", currentQuests));
	}


	@ApiDocumentResponse
	@Operation
	@GetMapping("/getItem/{questId}")
	public ResponseEntity<? extends BaseResponseBody> getQuestItem(
		@RequestAttribute("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
			String userId,
		@PathVariable
		Long questId
	) {
		// 아바타가 클리어한 퀘스트 추가
		avatarQuestService.insertItem(userId, new AvatarItemPostRequest("quest", questId));

		// 다음 퀘스트 정보 및 보상 아이템 추가
		var nextQuest = questService.selectNextQuest(userId, questId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "ok", nextQuest));
	}
}
