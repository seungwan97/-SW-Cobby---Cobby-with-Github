package com.cobby.main.quest.api.controller;

import com.cobby.main.quest.api.dto.request.QuestPostRequest;
import com.cobby.main.quest.api.dto.request.QuestPutRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.quest.api.service.QuestService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/quests")
public class QuestController {

	private final QuestService questService;

	@GetMapping
	public ResponseEntity<? extends BaseResponseBody> getAllQuests() {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "OK", questService.selectAllQuest()));
	}

	@GetMapping("/{questId}")
	public ResponseEntity<? extends BaseResponseBody> getQuest(@PathVariable Integer questId) {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "OK", questService.selectQuest(questId)));
	}

	@PostMapping
	public ResponseEntity<? extends BaseResponseBody> createQuest(@RequestBody QuestPostRequest questInfo) {
		questService.insertQuest(questInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Created", "생성되었습니다."));
	}

	@PutMapping
	public ResponseEntity<? extends BaseResponseBody> updateQuest(@RequestBody QuestPutRequest questInfo) {
		questService.updateQuest(questInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Updated", "수정되었습니다."));
	}

	@DeleteMapping("/{questId}")
	public ResponseEntity<? extends BaseResponseBody> deleteQuest(@PathVariable Integer questId) {
		questService.deleteQuest(questId);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Deleted", "삭제되었습니다."));
	}

}
