package com.cobby.main.title.api.controller;

import com.cobby.main.title.api.dto.request.TitlePostRequest;
import com.cobby.main.title.api.dto.request.TitlePutRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.title.api.service.TitleService;

import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/characters/title")
public class TitleController {

	private final TitleService titleService;

	@GetMapping
	public ResponseEntity<? extends BaseResponseBody> getAllTitles() {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "OK", titleService.selectAllTitle()));
	}

	@PostMapping
	public ResponseEntity<? extends BaseResponseBody> createTitle(@RequestBody TitlePostRequest titleInfo) {
		titleService.insertTitle(titleInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Created", "생성되었습니다."));
	}

	@PutMapping
	public ResponseEntity<? extends BaseResponseBody> updateTitle(@RequestBody TitlePutRequest titleInfo) {
		titleService.updateTitle(titleInfo);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Updated", "수정되었습니다."));
	}

	@DeleteMapping("/{titleId}")
	public ResponseEntity<? extends BaseResponseBody> deleteTitle(@PathVariable int titleId) {
		titleService.deleteTitle(titleId);
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Deleted", "삭제되었습니다."));
	}

}
