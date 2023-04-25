package com.cobby.main.title.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.title.api.service.TitleService;

import lombok.RequiredArgsConstructor;

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
	public ResponseEntity<? extends BaseResponseBody> createTitle() {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Created", "생성되었습니다."));
	}

	@PutMapping
	public ResponseEntity<? extends BaseResponseBody> updateTitle() {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Updated", "수정되었습니다."));
	}

	@DeleteMapping
	public ResponseEntity<? extends BaseResponseBody> deleteTitle() {
		return ResponseEntity.ok().body(new BaseResponseBody<>(200, "Deleted", "삭제되었습니다."));
	}

}
