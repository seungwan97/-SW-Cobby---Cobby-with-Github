package com.cobby.main.stat.api.service.impl;

import org.springframework.stereotype.Service;

import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.stat.api.dto.request.StatPostRequest;
import com.cobby.main.stat.api.dto.request.StatSubscribeRequest;
import com.cobby.main.stat.api.dto.response.StatResponse;
import com.cobby.main.stat.api.service.StatService;
import com.cobby.main.stat.db.repository.StatRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

	private final StatRepository statRepository;

	@Override
	public void subscribeStatInfo(StatSubscribeRequest statSubscribeRequest) {
		var stat = statRepository.findById(statSubscribeRequest.userId()).orElseThrow(NotFoundException::new);
		StatPostRequest statPostRequest = statSubscribeRequest.statPostRequest();

		var updateStat = stat.toBuilder()
			.commitCnt(statPostRequest.commitCnt())
			.starCnt(statPostRequest.starCnt())
			.prCnt(stat.getPrCnt())
			.issueCnt(stat.getIssueCnt())
			.build();
		statRepository.save(updateStat);
	}

	@Override
	public StatResponse getStatInfo(String userId) {
		var stat = statRepository.findById(userId).orElseThrow(NotFoundException::new);

		return StatResponse.builder()
			.commitCnt(stat.getCommitCnt().intValue())
			.starCnt(stat.getStarCnt().intValue())
			.prCnt(stat.getPrCnt().intValue())
			.followerCnt(stat.getFollowerCnt().intValue())
			.issueCnt(stat.getIssueCnt().intValue())
			.build();
	}

}
