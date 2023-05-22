package com.cobby.main.badge.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.badge.api.service.BadgeService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/badge")
public class BadgeController {

	private final BadgeService badgeService;

	@GetMapping("{nickname}") // 회원 svg 조회
	public ResponseEntity<String> getBadge(@PathVariable String nickname, @RequestParam(required = false, defaultValue = "0") int theme) {
		// username에 해당하는 정보를 이용해 SVG 데이터를 생성한다.
		String svgData = badgeService.getBadge(nickname, theme);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("image/svg+xml"));
		return new ResponseEntity<>(svgData, headers, HttpStatus.OK);
	}
}
