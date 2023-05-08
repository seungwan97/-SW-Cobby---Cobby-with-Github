package com.cobby.main.avatar.api.controller;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.service.AvatarCostumeService;
import com.cobby.main.avatar.api.service.AvatarItemService;
import com.cobby.main.avatar.api.service.AvatarQuestService;
import com.cobby.main.avatar.api.service.AvatarTitleService;
import com.cobby.main.common.response.BaseResponseBody;
import com.cobby.main.common.util.ApiDocumentResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/avatars/inventories")
public class AvatarItemController {
	private final AvatarCostumeService avatarCostumeService;
	private final AvatarTitleService avatarTitleService;
	private final AvatarQuestService avatarQuestService;

	@GetMapping("/{itemType}/{itemId}")
	public ResponseEntity<? extends BaseResponseBody> getAvatarItem(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId,
		@PathVariable @NotBlank(message = "필수 입력 항목입니다. (costume / title / quest)")
		String itemType,
		@PathVariable @Positive(message = "필수 입력 항목입니다. (양수)")
		Long itemId) {

		var avatarItemService = pickServiceByItemType(itemType);

		var item = avatarItemService.selectItem(userId, itemId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", item));
	}

	@GetMapping("/{itemType}")
	public ResponseEntity<? extends BaseResponseBody> getAllAvatarItems(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId,
		@PathVariable
		@NotBlank(message = "필수 입력 항목입니다. (costume / title / quest)")
		String itemType) {

		var avatarItemService = pickServiceByItemType(itemType);

		var items = avatarItemService.selectAllItems(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", items));
	}

	@ApiDocumentResponse
	@Operation(summary = "인벤토리 아이템 추가", description = "아바타의 인벤토리에 아이템을 추가합니다.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<? extends BaseResponseBody> createAvatarItem(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId,
		@RequestBody @Valid AvatarItemPostRequest itemInfo,
		HttpServletRequest request) {

		var avatarItemService = pickServiceByItemType(itemInfo.itemType());

		var avatarCostumeId = avatarItemService.insertItem(userId, itemInfo);

		var location = URI.create(request.getRequestURI() + "/" + avatarCostumeId);

		var successMessage = "보유 아이템을 성공적으로 추가했습니다. (type=" + itemInfo.itemType() + ", ID=" + avatarCostumeId + ")";

		return ResponseEntity
			.created(location)
			.body(new BaseResponseBody<>(201, "created", successMessage));
	}

	@ApiDocumentResponse
	@Operation(summary = "아바타 도전과제 목록 조회", description = "아바타의 현재 도전과제 목록을 조회합니다.")
	@GetMapping(value = "/quests/current")
	public ResponseEntity<? extends BaseResponseBody> getAvatarCurrentQuests(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId
	) {
		var currentQuests = avatarQuestService.selectAvatarCurrentQuests(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "ok", currentQuests));
	}


	private AvatarItemService pickServiceByItemType(String type) {
		return switch (type) {
			case "costume" -> this.avatarCostumeService;
			case "title" -> this.avatarTitleService;
			case "quest" -> this.avatarQuestService;
			default -> null;
		};
	}

}
