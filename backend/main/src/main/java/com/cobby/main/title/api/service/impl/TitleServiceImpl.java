package com.cobby.main.title.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.repository.QuestRepository;
import com.cobby.main.title.api.dto.request.TitlePostRequest;
import com.cobby.main.title.api.dto.request.TitlePutRequest;
import com.cobby.main.title.api.dto.response.TitleGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.cobby.main.title.api.service.TitleService;
import com.cobby.main.title.db.entity.Title;
import com.cobby.main.title.db.repository.TitleRepository;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

	private final TitleRepository titleRepository;
	private final QuestRepository questRepository;

	@Override
	public TitleGetResponse selectTitle(Long titleId) {
		return TitleGetResponse.builder()
			.title(titleRepository.findById(titleId).orElseThrow(NotFoundException::new))
			.build();
	}

	@Override
	public List<TitleGetResponse> selectAllTitles() {
		return titleRepository.findAll()
			.stream()
			.map(title -> TitleGetResponse.builder().title(title).build())
			.toList();
	}

	@Override
	public Long insertTitle(TitlePostRequest titleInfo) {
		var quest = questRepository.findById(titleInfo.questId()).orElseThrow(NotFoundException::new);

		var title = Title.builder()
				.quest(quest)
				.name(titleInfo.name())
				.explanation(titleInfo.explanation())
				.build();

		return titleRepository.save(title).getTitleId();
	}

	@Override
	public Long updateTitle(TitlePutRequest titleInfo) {
		var title = titleRepository.findById(titleInfo.titleId()).orElseThrow(NotFoundException::new);
		var quest = questRepository.findById(titleInfo.questId()).orElseThrow(NotFoundException::new);

		var updateTitle = title.toBuilder()
				.quest(quest)
				.name(titleInfo.name())
				.explanation(titleInfo.explanation())
				.build();

		return titleRepository.save(updateTitle).getTitleId();
	}

	@Override
	public Long deleteTitle(Long titleId) {
		titleRepository.findById(titleId).orElseThrow(NotFoundException::new);
		titleRepository.deleteById(titleId);
		return titleId;
	}
}
