package com.cobby.main.title.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cobby.main.title.api.service.TitleService;
import com.cobby.main.title.db.entity.Title;
import com.cobby.main.title.db.repository.TitleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

	private final TitleRepository titleRepository;

	@Override
	public List<Title> selectAllTitle() {
		return titleRepository.findAll();
	}
}
