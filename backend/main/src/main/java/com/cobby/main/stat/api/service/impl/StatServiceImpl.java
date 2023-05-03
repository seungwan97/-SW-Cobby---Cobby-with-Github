package com.cobby.main.stat.api.service.impl;

import org.springframework.stereotype.Service;

import com.cobby.main.stat.api.dto.request.StatPostRequest;
import com.cobby.main.stat.api.dto.request.StatSubscribeRequest;
import com.cobby.main.stat.api.service.StatService;
import com.cobby.main.stat.db.entity.Stat;
import com.cobby.main.stat.db.repository.StatRepository;
import com.cobby.main.common.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

	private final StatRepository statRepository;

	@Override
	public void subscribeUserInfo(StatSubscribeRequest statSubscribeRequest) {
		var stat = statRepository.findById(statSubscribeRequest.getUserId()).orElseThrow(NotFoundException::new);
		StatPostRequest statPostRequest = statSubscribeRequest.getStatPostRequest();

		var updateStat = stat.toBuilder()
			.commitCnt(statPostRequest.commitCnt())
			.starCnt(statPostRequest.starCnt())
			.forkCnt(statPostRequest.forkCnt())
			.prCnt(stat.getPrCnt())
			.issueCnt(stat.getIssueCnt())
			.build();
		statRepository.save(updateStat);
	}

}
