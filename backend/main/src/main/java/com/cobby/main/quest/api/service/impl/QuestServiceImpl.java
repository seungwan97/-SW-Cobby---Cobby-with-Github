package com.cobby.main.quest.api.service.impl;

import java.util.List;

import com.cobby.main.avatar.db.repository.AvatarRepository;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.quest.api.dto.request.QuestPostRequest;
import com.cobby.main.quest.api.dto.request.QuestPutRequest;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import org.springframework.stereotype.Service;

import com.cobby.main.avatar.api.dto.response.AvatarQuestGetResponse;
import com.cobby.main.quest.api.service.QuestService;
import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.quest.db.repository.QuestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {

	private final QuestRepository questRepository;

	@Override
	public QuestGetResponse selectQuest(Integer questId) {
		return QuestGetResponse.builder()
			.quest(questRepository.findById(questId).orElseThrow(NotFoundException::new))
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
	public void insertQuest(QuestPostRequest questInfo) {
		var quest = Quest.builder()
				.questName(questInfo.questName())
				.questType(questInfo.questType())
				.questGoal(questInfo.questGoal())
				.costumes(questInfo.costumes())
				.titles(questInfo.titles())
				.build();

		questRepository.save(quest);
	}

	@Override
	public void updateQuest(QuestPutRequest questInfo) {
		var quest = questRepository.findById(questInfo.questId()).orElseThrow(NotFoundException::new);
		var updateQuest = quest.toBuilder()
				.questName(questInfo.questName())
				.questType(questInfo.questType())
				.questGoal(questInfo.questGoal())
				.titles(questInfo.titles())
				.costumes(questInfo.costumes())
				.build();

		questRepository.save(updateQuest);
	}

	@Override
	public void deleteQuest(Integer questId) {
		questRepository.findById(questId).orElseThrow(NotFoundException::new);
		questRepository.deleteById(questId);
	}
}
