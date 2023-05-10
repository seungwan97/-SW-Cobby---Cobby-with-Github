package com.cobby.main.avatar.api.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.cobby.main.avatar.api.dto.response.AvatarQuestGetResponse;
import com.cobby.main.avatar.api.service.AvatarInventoryService;
import com.cobby.main.avatar.api.service.AvatarQuestService;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.common.util.ApiDocumentResponse;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.api.service.QuestService;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/avatars")
@Tag(name = "아바타", description = "아바타과제 관련 API 문서입니다.")
public class AvatarController {

	private final AvatarService avatarService;
	private final AvatarInventoryService avatarInventoryService;
	private final AvatarQuestService avatarQuestService;

	// PathVariable 과 경로는 추후 로그인 모듈이 완성되면 Header 를 통해 찾게 되면 변경할 예정입니다.
	@ApiDocumentResponse
	@Operation(summary = "아바타 조회", description = "user ID로 아바타를 조회하는 메서드 입니다.")
	@GetMapping
	public ResponseEntity<? extends BaseResponseBody> getAvatar(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var costumes = avatarService.selectAvatar(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", costumes));
	}
	
	@ApiDocumentResponse
	@Operation(summary = "아바타 생성", description = "user ID로 고유한 아바타를 생성하는 메서드 입니다.")
	@PostMapping
	public ResponseEntity<? extends BaseResponseBody> createAvatar(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId,
		HttpServletRequest request) {

		var avatarId = avatarService.insertDefaultAvatar(userId);

		var successMessage = "기본 아바타를 성공적으로 생성했습니다. (ID=" + avatarId + ")";

		var location = URI.create(request.getRequestURI() + "/" + avatarId);

		return ResponseEntity
			.created(location)
			.body(new BaseResponseBody<>(201, "created", successMessage));
	}

	@ApiDocumentResponse
	@Operation(summary = "아바타 정보 수정", description = "user ID에 해당하는 아바타 정보를 업데이트 하는 메서드입니다.")
	@PatchMapping
	public ResponseEntity<? extends BaseResponseBody> updateAvatar(
		@RequestBody Map<String, Integer> avatarUpdateInfo,
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var avatarId = avatarService.updateAvatar(userId, avatarUpdateInfo);

		var successMessage = "아바타 정보를 성공적으로 변경했습니다. (ID=" + avatarId + ")";

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", successMessage));
	}

	@ApiDocumentResponse
	@Operation(summary = "아바타 정보 초기화", description = "user ID에 해당하는 아바타를 초기화하는 메서드 입니다.")
	@GetMapping("/reset")
	public ResponseEntity<? extends BaseResponseBody> resetAvatar(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var avatarId = avatarService.resetAvatar(userId);

		var successMessage = "아바타 정보를 성공적으로 초기화했습니다. (ID=" + avatarId + ")";

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", successMessage));
	}

	@ApiDocumentResponse
	@Operation(summary = "아바타 정보 삭제", description = "user ID에 해당하는 아바타 정보를 삭제하는 메서드 입니다.")
	@DeleteMapping
	public ResponseEntity<? extends BaseResponseBody> deleteAvatar(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var avatarId = avatarService.deleteAvatar(userId);

		var successMessage = "아바타 정보를 성공적으로 삭제했습니다. (ID=" + avatarId + ")";

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", successMessage));
	}

	@ApiDocumentResponse
	@Operation(summary = "인벤토리 조회", description = "아바타가 갖고있는 코스튬을 조회하는 메서드 입니다.")
	@PostMapping(value = "/inventories", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<? extends BaseResponseBody> createAvatarInventoryItem(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId,
		@RequestBody @Valid AvatarItemPostRequest itemInfo,
		HttpServletRequest request) {

		var avatarCostumeId = avatarInventoryService.insertAvatarInventoryItem(userId, itemInfo);

		var location = URI.create(request.getRequestURI() + "/" + avatarCostumeId);

		var successMessage = "아바타에 코스튬을 성공적으로 추가했습니다. (ID=" + avatarCostumeId + ")";

		return ResponseEntity
			.created(location)
			.body(new BaseResponseBody(201, "created", successMessage));
	}

	@ApiDocumentResponse
	@Operation(summary = "아바타 도전과제 목록 조회", description = "아바타의 현재 도전과제 목록을 조회합니다.")
	@GetMapping(value = "/quests")
	public ResponseEntity<? extends BaseResponseBody> getAvatarQuestList(
		@RequestHeader("userId")
		// @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId
	) {
		var avatarQuestGetResponseList = avatarQuestService.selectAvatarQuests(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody(200, "ok", avatarQuestGetResponseList));
	}
}
