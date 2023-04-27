package com.cobby.main.user.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cobby.main.stat.db.entity.Stat;
import com.cobby.main.stat.db.repository.StatRepository;
import com.cobby.main.user.api.dto.response.UserMainResponse;
import com.cobby.main.user.api.service.UserService;
import com.cobby.main.user.db.entity.State;
import com.cobby.main.user.db.entity.User;
import com.cobby.main.user.db.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final StatRepository statRepository;

	@Override
	public UserMainResponse getUserInfo(String userId) {
		Optional<User> oUser = userRepository.findById(userId);
		User user = oUser.orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
		Optional<Stat> oStats = statRepository.findById(userId);
		Stat stat = oStats.orElseThrow(() -> new IllegalArgumentException("stat doesn't exist"));

		UserMainResponse userMainResponse =UserMainResponse.builder()
			.nickname(user.getNickname())
			.commit_cnt(stat.getCommitCnt().intValue())
			.star_cnt(stat.getStarCnt().intValue())
			.fork_cnt(stat.getForkCnt().intValue())
			.build();
		return userMainResponse;
	}

	@Override
	public void signOutUserInfo(String userId) {
		Optional<User> oUser = userRepository.findById(userId);
		User user = oUser.orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
		log.info(user.toString());
		log.info(String.valueOf(user.getLastModifiedAt()));

		user = user.toBuilder()
			.state(State.X)
			.build();

		log.info(user.toString());

		userRepository.save(user);
	}
}
