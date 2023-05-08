package com.cobby.main.avatar.api.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.cobby.main.avatar.api.dto.response.AvatarQuestGetResponse;
import com.cobby.main.avatar.api.dto.response.CurrentQuest;
import com.cobby.main.avatar.api.service.AvatarQuestService;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.avatar.db.entity.AvatarQuest;
import com.cobby.main.avatar.db.repository.AvatarQuestRepository;
import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.common.exception.BaseRuntimeException;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import com.cobby.main.quest.api.service.QuestService;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.quest.db.repository.QuestRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class AvatarQuestServiceImpl implements AvatarQuestService {

	private final AvatarQuestRepository avatarQuestRepository;
	private final AvatarService avatarService;
	private final QuestService questService;
	private final QuestRepository questRepository;
	private final AvatarRepository avatarRepository;

	@Override
	public Long insertItem(String avatarId, AvatarItemPostRequest itemInfo) {
		var avatar = avatarRepository.findById(avatarId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + avatarId + ")"));

		var quest = questRepository.findById(itemInfo.itemId())
			.orElseThrow(() -> new IllegalArgumentException("아이템 정보가 없습니다. (type=" +
				itemInfo.itemType() + ", ID=" + itemInfo.itemId() + ")"));

		var avatarQuest = AvatarQuest.builder()
			.avatar(avatar)
			.quest(quest)
			.build();

		return avatarQuestRepository.save(avatarQuest).getAvatarQuestId();
	}

	@Override
	public List<AvatarQuestGetResponse> selectAllItems(String userId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var avatarQuests = avatarQuestRepository.findAllByAvatar_AvatarId(userId);

		if(avatarQuests.isEmpty()) {
			throw new BaseRuntimeException(HttpStatus.NOT_FOUND, "사용자가 보유한 코스튬이 없습니다.");
		}

		return avatarQuests.stream()
			.map(quest ->
				AvatarQuestGetResponse.builder()
					.avatarQuestId(quest.getAvatarQuestId())
					.quest(quest.getQuest())
					.build())
			.toList();
	}

	@Override
	public AvatarQuestGetResponse selectItem(String userId, Long itemId) {
		var avatar = avatarRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("아바타 정보가 없습니다. (ID=" + userId + ")"));

		var avatarQuest = avatarQuestRepository.findById(itemId)
			.orElseThrow(() -> new IllegalArgumentException("코스튬 정보가 없습니다. (ID=" + itemId + ")"));

		return AvatarQuestGetResponse.builder()
			.avatarQuestId(avatarQuest.getAvatarQuestId())
			.quest(avatarQuest.getQuest())
			.build();
	}

	@Override
	public List<CurrentQuest> selectAvatarCurrentQuests(String userId) {

		CurrentQuest[] currentQuests = new CurrentQuest[4];
		try {
			// 1. 현재 달성 현황
			// 1-1. 연속 출석 일자, 연속 커밋 일자 서버통신 받아오기

			// 0 : 레벨, 1 : 커밋, 2 : 출석, 3 : 아이템 순서
			int[] progressReq = new int[4];

			// user서버 연동 후 설정
			// 추후 yml에 설정l
			// String url = "";
			// OkHttpClient client = new OkHttpClient();
			//
			// Request.Builder builder = new Request.Builder()
			// 	.url(url)
			// 	.addHeader("userId", userId);
			// Request request = builder.build();
			//
			// Response response = client.newCall(request).execute();
			//
			// JSONObject jsonObject = new JSONObject(response.body().string());
			//
			// // progressReq[1] = Integer.parseInt(jsonObject.getString("relayCommit"));
			// // progressReq[2] = Integer.parseInt(jsonObject.getString("relayAttend"));

			progressReq[1] = 1;
			progressReq[2] = 1;

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
			var avatarQuestList = selectAllItems(userId);

			// 4. 항목별 완료한 도전과제의 달성 조건 받아옴
			// 0 : 레벨, 1 : 커밋, 2 : 출석, 3 : 아이템 순서
			int[] goal = new int[4];
			for (var aqList : avatarQuestList) {
				int aqGoal = aqList.getQuest().getQuestGoal();
				switch (aqList.getQuest().getQuestType()) {
					case LEVEL -> goal[0] = Math.max(goal[0], aqGoal);
					case COMMIT -> goal[1] = Math.max(goal[1], aqGoal);
					case ATTENDANCE -> goal[2] = Math.max(goal[2], aqGoal);
					case ITEM -> goal[3] = Math.max(goal[3], aqGoal);
				}
			}

			// 5. 완료한 도전과제의 다음 달성 조건 탐색하여 반환
			idx = 0;
			for (List<QuestGetResponse> qList : questList) {
				for (QuestGetResponse quest : qList) {
					if (goal[idx] < quest.getQuestGoal()) {
						int progress = Math.min(Math.round(((float)progressReq[idx] / quest.getQuestGoal()) * 100), 100);

						Object award = "";

						if (quest.getCostumes().size() == 0 && quest.getTitles().size() == 0)
							award = "none";
						else if (quest.getCostumes().size() > 0)
							award = quest.getCostumes().get(0);
						else
							award = quest.getTitles().get(0);

						currentQuests[idx] = CurrentQuest.builder()
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

				// Null이라면 마지막 단계까지 달성한 것이므로 -1
				if (Objects.isNull(currentQuests[idx])) {
					// currentQuests[idx] = new AvatarQuestGetResponse().builder().questId(0).build();
					currentQuests[idx] = CurrentQuest.builder()
						.questId(-1L)
						.questName("달성 완료")
						.build();
				}
				idx++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Arrays.asList(currentQuests);
	}

}
