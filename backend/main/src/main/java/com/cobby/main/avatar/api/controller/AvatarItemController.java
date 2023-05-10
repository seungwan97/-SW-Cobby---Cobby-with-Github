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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Tag(name = "캐릭터가 보유한 아이템", description = "Cobby 캐릭터가 가진 코스튬, 칭호, 도전 과제와 관련된 API 문서입니다.")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/avatars/inventories")
public class AvatarItemController {
	private final AvatarCostumeService avatarCostumeService;
	private final AvatarTitleService avatarTitleService;
	private final AvatarQuestService avatarQuestService;

	@ApiDocumentResponse
	@Operation(summary = "아바타가 가진 특정 타입 아이템 하나 조회", description = "아이템 종류(코스튬 = costume, 칭호 = title, 도전 과제 = quest)와 종류별 ID 정보를 입력해 아이템을 조회합니다.")
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

	@ApiDocumentResponse
	@Operation(summary = "아바타가 가진 특정 타입 아이템 전체 조회", description = "아이템 종류(코스튬 = costume, 칭호 = title, 도전 과제 = quest)를 입력하면 해당 유저가 가진 특정 타입 아이템을 조회합니다.")
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

	private AvatarItemService pickServiceByItemType(String type) {
		return switch (type) {
			case "costume" -> this.avatarCostumeService;
			case "title" -> this.avatarTitleService;
			case "quest" -> this.avatarQuestService;
			default -> null;
		};
	}

}
