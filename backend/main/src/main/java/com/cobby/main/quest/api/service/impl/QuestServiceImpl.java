package com.cobby.main.quest.api.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.cobby.main.avatar.api.service.AvatarCostumeService;
import com.cobby.main.avatar.api.service.AvatarTitleService;
import com.cobby.main.avatar.db.repository.AvatarQuestRepository;
import com.cobby.main.quest.api.dto.response.CurrentQuest;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.quest.api.dto.request.QuestPostRequest;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cobby.main.quest.api.service.QuestService;
import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.quest.db.repository.QuestRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestServiceImpl implements QuestService {

	private final AvatarService avatarService;
	private final AvatarTitleService avatarTitleService;
	private final AvatarCostumeService avatarCostumeService;
	private final QuestRepository questRepository;
	private final AvatarQuestRepository avatarQuestRepository;

	@Value("{request.user}")
	private String USER_SERVER;

	@Override
	public QuestGetResponse selectQuest(Long questId) {
		return QuestGetResponse.builder()
			.quest(questRepository.findById(questId)
				.orElseThrow(NotFoundException::new))
			.build();
	}

	@Override
	public List<QuestGetResponse> selectAllQuest() {
		return questRepository.findAll()
			.stream()
			.map(quest -> QuestGetResponse.builder().quest(quest).build())
			.toList();
	}

	@Override
	public List<QuestGetResponse> selectAllQuestByQuestType(QuestCategory questCategory) {
		return questRepository.findAllByQuestTypeOrderByQuestGoal(questCategory)
			.stream()
			.map(quest -> QuestGetResponse.builder().quest(quest).build())
			.toList();
	}

	@Override
	public Long insertQuest(QuestPostRequest questInfo) {
		var quest = Quest.builder()
			.questName(questInfo.questName())
			.questType(questInfo.questType())
			.questGoal(questInfo.questGoal())
			.build();

		return questRepository.save(quest).getQuestId();
	}

	@Override
	public Long deleteQuest(Long questId) {
		questRepository.findById(questId).orElseThrow(NotFoundException::new);
		questRepository.deleteById(questId);
		return questId;
	}

	@Override
	public List<CurrentQuest> selectCurrentQuests(String userId) {

		CurrentQuest[] currentQuests = new CurrentQuest[4];
		try {
			// idx => 0 : 레벨, 1 : 커밋, 2 : 출석, 3 : 아이템 순서
			// 1. currentProgress : 현재 달성 현황
			// 2. 퀘스트 목록 불러옴
			int[] currentProgress = new int[4];
			var questList = new List[4];
			int idx = 0;

			for (QuestCategory category : QuestCategory.values()) {
				currentProgress[idx] = getCurrentProgress(userId, category);
				questList[idx++] = this.selectAllQuestByQuestType(category);
			}

			// 3. 아바타퀘스트 목록 불러옴
			var avatarQuestList = avatarQuestRepository.findAllByAvatar_AvatarId(userId);

			// 4. 항목별 완료한 도전과제의 달성 조건 받아옴
			// 0 : 레벨, 1 : 커밋, 2 : 출석, 3 : 아이템 순서
			int[] goal = new int[4];
			// 아바타가 달성한 각 유형별 퀘스트의 가장 큰 달성 조건을 산출
			for (var avatarQuest : avatarQuestList) {
				int aqGoal = avatarQuest.getQuest().getQuestGoal();
				switch (avatarQuest.getQuest().getQuestType()) {
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
						int progress =
							Math.min(
							Math.round(
								((float)currentProgress[idx] / quest.getQuestGoal()) * 100)
								, 100
						);

						// 퀘스트가 가지고 있는 보상의 유형(코스튬/칭호) 파악
						Object award = getAward(quest);

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

	@Override
	public CurrentQuest selectNextQuest(String userId, Long questId) {
		
		// 퀘스트 보상 아이템 DB에 추가
		var clearQuestEntity = questRepository.findById(questId)
			.orElseThrow(NotFoundException::new);
		
		if (!Objects.isNull(clearQuestEntity.getCostume())) {
			avatarCostumeService.insertItem(
				userId, new AvatarItemPostRequest(
					"costume",
						clearQuestEntity.getCostume().getCostumeId())
			);
		}
		else if (!Objects.isNull((clearQuestEntity.getTitle()))) {
			avatarTitleService.insertItem(
				userId, new AvatarItemPostRequest(
					"title",
					clearQuestEntity.getTitle().getTitleId())
			);
		}
		
		// 현재 클리어한 퀘스트
		var clearQuest = selectQuest(questId);
		var type = clearQuest.getQuestType();
		var goal = clearQuest.getQuestGoal();

		// 같은 타입의 보다 상위 조건의 퀘스트 가져옴 
		var findNextQuest = questRepository.findFirstByQuestTypeAndQuestGoalGreaterThanOrderByQuestGoal(type, goal);
		
		// 상위 조건의 퀘스트가 존재하지 않을 시 달성 완료
		if (!findNextQuest.isPresent()) {
			return CurrentQuest.builder()
				.questId(-1L)
				.questName("달성 완료")
				.build();
		}

		var nextQuest = findNextQuest
			.map(quest -> QuestGetResponse.builder().quest(quest).build())
			.orElseThrow(NotFoundException::new);

		// 진행상황 계산
		int currentProgress = getCurrentProgress(userId, type);
		int progress =
			Math.min(
				Math.round(
					((float)currentProgress / nextQuest.getQuestGoal()) * 100)
				, 100
			);

		// 다음 퀘스트 내용 반환
		return CurrentQuest.builder()
			.questId(nextQuest.getQuestId())
			.questName(nextQuest.getQuestName())
			.questType(nextQuest.getQuestType())
			.questGoal(nextQuest.getQuestGoal())
			.progress(progress)
			.award(getAward(nextQuest))
			.build();
	}

	int getCurrentProgress(String userId, QuestCategory type) {
		int progress = 1;
		try {
			switch (type) {
				case LEVEL -> {
					AvatarGetResponse avatar = avatarService.selectAvatar(userId);
					progress = avatar.getLevel();
				}
				case COMMIT -> {
					progress = getRelayCnt(userId, "commit");
				}
				case ATTENDANCE -> {
					progress = getRelayCnt(userId, "attendance");
				}
				case ITEM -> {
					AvatarGetResponse avatar = avatarService.selectAvatar(userId);
					progress = avatar.getCostumes().size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return progress;
	}

	int getRelayCnt(String userId, String kind) {
		int relayCnt = 0;
		try {
			OkHttpClient client = new OkHttpClient();

			Request.Builder builder = new Request.Builder()
				.url(USER_SERVER + "/api/user/activityLog/" + kind)
				.addHeader("userId", userId);
			Request request = builder.build();

			Response response = client.newCall(request).execute();

			JSONObject jsonObject = new JSONObject(response.body().string());
			relayCnt = jsonObject.getJSONObject("content").getInt("relayCnt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return relayCnt;
	}

	Object getAward(QuestGetResponse quest) {
		if (quest.getCostume() == null && quest.getTitle() == null)
			return "none";
		else if (quest.getCostume() != null)
			return quest.getCostume();
		else if (quest.getTitle() != null)
			return quest.getTitle();
		else
			return "none";
	}
}
