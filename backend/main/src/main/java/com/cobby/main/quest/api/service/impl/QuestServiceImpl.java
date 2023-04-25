package com.cobby.main.quest.api.service.impl;

import java.util.List;

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
	public List<Quest> selectAllQuest( ) {
		return questRepository.findAll();
	}
}
