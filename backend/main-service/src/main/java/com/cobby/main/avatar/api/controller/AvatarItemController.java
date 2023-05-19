package com.cobby.main.avatar.api.controller;

import java.net.URI;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cobby.main.avatar.api.dto.request.AvatarCostumePatchRequest;
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
@CrossOrigin
@RequestMapping("api/main/avatars/inventories")
public class AvatarItemController {
	private final AvatarCostumeService avatarCostumeService;
	private final AvatarTitleService avatarTitleService;
	private final AvatarQuestService avatarQuestService;

	@ApiDocumentResponse
	@Operation(summary = "아바타가 가진 코스튬 카테고리 별 조회", description = "코스튬의 특정 카테고리(머리 = HEAD, 몸통 = BODY, 효과 = EFFECT) 목록을 조회합니다.")
	@GetMapping("/costumes/{itemType}")
	public ResponseEntity<? extends BaseResponseBody> getAvatarCostumeByCategory(
		@RequestAttribute("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId,
		@PathVariable @NotBlank(message = "필수 입력 항목입니다. (HEAD / BODY / EFFECT)")
		String itemType) {

		var item = avatarCostumeService.selectAllCostumesByType(userId, itemType);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", item));
	}

	@ApiDocumentResponse
	@Operation(summary = "아바타가 가진 특정 타입 아이템 전체 조회", description = "아이템 종류(코스튬 = costume, 칭호 = title, 도전 과제 = quest)를 입력하면 해당 유저가 가진 특정 타입 아이템을 조회합니다.")
	@GetMapping("/{itemType}")
	public ResponseEntity<? extends BaseResponseBody> getAllAvatarItems(
		@RequestAttribute("userId")
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

	@Hidden
	@ApiDocumentResponse
	@Operation(summary = "#####인벤토리에 아이템(코스튬/칭호/도전 과제) 추가#####", description = "아이템 종류(코스튬 = costume, 칭호 = title, 도전 과제 = quest)와 그 아이템의 ID를 입력하면 해당 유저의 인벤토리에 추가합니다.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<? extends BaseResponseBody> createAvatarItem(
		@RequestAttribute("userId")
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
	@Operation(summary = "인벤토리에 새로운 코스튬 열어보기", description = "코스튬의 상태를 새로 획득한 코스튬에서 일반 코스튬 상태로 변경합니다.")
	@PatchMapping
	public ResponseEntity<? extends BaseResponseBody> openAvatarItem(
		@RequestAttribute("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId,
		@RequestBody @Valid AvatarCostumePatchRequest costumeInfo) {

		var avatarCostumeId = avatarCostumeService.openItem(userId, costumeInfo);

		var successMessage = "새로운 코스튬을 성공적으로 열어보았습니다. (ID=" + avatarCostumeId + ")";

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", successMessage));
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
