package com.cobby.main.quest.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.quest.api.dto.request.QuestPostRequest;
import com.cobby.main.quest.api.dto.request.QuestPutRequest;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import org.springframework.stereotype.Service;

import com.cobby.main.quest.api.service.QuestService;
import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.repository.QuestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {

	private final QuestRepository questRepository;

	@Override
	public QuestGetResponse selectQuest(int questId) {
		return questRepository.findById(questId).stream().map(Quest::toDto).findFirst().orElseThrow(NotFoundException::new);
	}

	@Override
	public List<QuestGetResponse> selectAllQuest( ) {
		return questRepository.findAll().stream().map(Quest::toDto).collect(Collectors.toList());
	}

	@Override
	public void insertQuest(QuestPostRequest questInfo) {
		var quest = Quest.builder()
				.questName(questInfo.getQuestName())
				.questType(questInfo.getQuestType())
				.questCode(questInfo.getQuestCode())
				.titles(questInfo.getTitles())
				.build();

		questRepository.save(quest);
	}

	@Override
	public void updateQuest(QuestPutRequest questInfo) {
		var quest = questRepository.findById(questInfo.getQuestId()).orElseThrow(NotFoundException::new);
		var updateQuest = quest.toBuilder()
				.questName(questInfo.getQuestName())
				.questType(questInfo.getQuestType())
				.questCode(questInfo.getQuestCode())
				.titles(questInfo.getTitles())
				.build();

		questRepository.save(updateQuest);
	}

	@Override
	public void deleteQuest(int questId) {
		var quest = questRepository.findById(questId).orElseThrow(NotFoundException::new);

		questRepository.deleteById(questId);
	}
}
