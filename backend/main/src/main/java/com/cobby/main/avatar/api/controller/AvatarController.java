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
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.api.service.QuestService;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/avatars")
public class AvatarController {

	private final AvatarService avatarService;
	private final AvatarInventoryService avatarInventoryService;
	private final AvatarQuestService avatarQuestService;
	private final QuestService questService;

	// PathVariable 과 경로는 추후 로그인 모듈이 완성되면 Header 를 통해 찾게 되면 변경할 예정입니다.
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

	@GetMapping(value = "/quests")
	public ResponseEntity<? extends BaseResponseBody> getAvatarQuestList(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId
	) {
		AvatarQuestGetResponse[] avatarQuestGetResponseList = new AvatarQuestGetResponse[4];

		try {
			// 1. 현재 달성 현황
			// 1-1. 연속 출석 일자, 연속 커밋 일자 서버통신 받아오기

			// 0 : 레벨, 1 : 커밋, 2 : 출석, 3 : 아이템 순서
			int[] progressReq = new int[4];

			// 추후 yml에 설정
			String url = "";
			OkHttpClient client = new OkHttpClient();

			Request.Builder builder = new Request.Builder()
				.url(url)
				.addHeader("userId", userId);
			Request request = builder.build();

			Response response = client.newCall(request).execute();

			JSONObject jsonObject = new JSONObject(response.body().string());

			progressReq[1] = Integer.parseInt(jsonObject.getString("relayCommit"));
			progressReq[2] = Integer.parseInt(jsonObject.getString("relayAttend"));

			// 1-2. 레벨, 아이템 갯수
			AvatarGetResponse avatar = avatarService.selectAvatar(userId);
			progressReq[0] = avatar.getLevel();
			progressReq[3] = avatar.getCostumes().size();

			// 2. 퀘스트 목록 불러옴(추후 캐싱 가능할듯?)
			// 0 : 레벨, 1 : 커밋, 2 : 출석, 3 : 아이템 순서
			var questList = new List[4];

			int idx = 0;
			for (QuestCategory category : QuestCategory.values()) {
				questList[idx++] = questService.selectAllQuestByQuestType(category);
			}

			// 3. 아바타퀘스트 목록 불러옴
			var avatarQuestList = avatarQuestService.selectAllAvatarQuests(userId);

			// 4. 항목별 완료한 도전과제의 달성 조건 받아옴
			// 0 : 레벨, 1 : 커밋, 2 : 출석, 3 : 아이템 순서
			int[] goal = new int[4];
			for (var aqList : avatarQuestList) {
				int aqGoal = aqList.getQuest().getQuestGoal();
				switch(aqList.getQuest().getQuestType()) {
					case LEVEL: goal[0] = Math.max(goal[0], aqGoal); break;
					case COMMIT: goal[1] = Math.max(goal[1], aqGoal); break;
					case ATTENDANCE: goal[2] = Math.max(goal[2], aqGoal); break;
					case ITEM: goal[3] = Math.max(goal[3], aqGoal); break;
				}
			}

			// 5. 완료한 도전과제의 다음 달성 조건 탐색하여 반환
			idx = 0;
			for (List<QuestGetResponse> qList : questList) {
				for (QuestGetResponse quest : qList) {
					if (goal[idx] < quest.getQuestGoal()) {
						int progress = Math.round(((float)progressReq[idx] / goal[idx]) * 100);
						Object award;
						if (Objects.isNull(quest.getCostumes().get(0))) award = quest.getTitles().get(0);
						else award = quest.getCostumes().get(0);
						// progress 추가 방안 검토
						avatarQuestGetResponseList[idx] = AvatarQuestGetResponse.builder()
							.questId(quest.getQuestId())
							.questName(quest.getQuestName())
							.questType(quest.getQuestType())
							.questGoal(quest.getQuestGoal())
							.progress(progress)
							.award(award)
						.build();
						break;
					}
				}

				// Null이라면 마지막 단계까지 달성한 것이므로 빈 객체
				if (Objects.isNull(avatarQuestGetResponseList[idx])) {
					avatarQuestGetResponseList[idx] = new AvatarQuestGetResponse();
				}
				idx++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody(200, "ok", avatarQuestGetResponseList));
	}
}
