package com.cobby.store.asset.api.controller;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cobby.store.asset.api.service.AssetService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/resources")
public class AssetController {

	@Value("${request.url}")
	private String REQUEST_URL;
	private final AssetService assetService;

	@GetMapping("/assets/{assetId}")
	public ResponseEntity<Object> getAsset(
		@PathVariable("assetId")
		@NotBlank(message = "필수 입력 항목입니다. ")
		String assetId
	) throws IOException {
		var asset = assetService.selectAsset(assetId);

		return ResponseEntity
			.ok()
			.header("Content-Type", asset.contentType())
			.body(asset.resource());
	}

	@PostMapping(value = "/upload")
	public ResponseEntity<String> createAsset(
		@RequestParam("pngFile") MultipartFile pngFile,
		@RequestParam("gifFile") MultipartFile gifFile,
		HttpServletRequest request) {
		System.out.println("asset : " + pngFile);
		System.out.println("asset : " + gifFile);
		var assetId = assetService.insertAsset(pngFile);

		// 생성된 자원을 조회할 수 있는 uri 경로 생성 (201 응답 양식)
		var location = URI.create(request.getRequestURI() + "/assets/" + assetId);

		var url = REQUEST_URL + assetId;
		System.out.println("location : " + location);
		System.out.println("url : " + url);
		return ResponseEntity
			.created(location)
			.body(url);
	}
}
