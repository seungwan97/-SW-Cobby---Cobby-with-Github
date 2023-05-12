package com.cobby.main.activitylog.api.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.cobby.main.activitylog.api.dto.response.ActivityLogCommitResponse;
import com.cobby.main.activitylog.api.service.ActivityLogService;
import com.cobby.main.activitylog.db.entity.ActivityLog;
import com.cobby.main.activitylog.db.entity.ActivityType;
import com.cobby.main.activitylog.db.repository.ActivityLogRepository;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.user.db.entity.User;
import com.cobby.main.user.db.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

	private final UserRepository userRepository;
	private final ActivityLogRepository activityLogRepository;


	// 1. webhook 걸릴 때 로그라 그냥 다 찍히는데 commit webhook 걸리면 db에 저장됩니다.
	@Override
	public void webhookCreate(Map<String, String> headers, String payload) {

		for (Map.Entry<String, String> entry : headers.entrySet()) {

			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(payload, JsonObject.class);
			if(entry.getValue().equals("push")){ // commit push면
				// ( commit: pusher )
				String name = jsonObject.getAsJsonObject("pusher").get("name").getAsString();
				var activityLog = ActivityLog.builder()
					.activityType(ActivityType.COMMIT)
					.user(findUser(name))
					.relayCnt(findDate(name))
					.build();

				activityLogRepository.save(activityLog);
			}
		}

	}

	// 2. 출석 api 요청을 받으면 그냥 바로 활동 일자 비교해서 일이 다르면 relayCnt 하나 증가
	@Override
	public ActivityLog getActivityLogInfo(String userId) {
		var activityLogList = activityLogRepository.findByUserIdOrderByIdDesc(userId);
		var relayCnt = 1L;
		for(ActivityLog activityLog : activityLogList){
			if(activityLog.getActivityType() == ActivityType.ATTENDANCE) {
				if(activityLog.getLastModifiedAt().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
					relayCnt = activityLog.getRelayCnt();
					break;
				}
				else if(activityLog.getLastModifiedAt().getDayOfMonth()+1 == LocalDateTime.now().getDayOfMonth()) {
					relayCnt++;
					break;
				}
			}
		}

		var user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

		var activityLog = ActivityLog.builder()
			.activityType(ActivityType.ATTENDANCE)
			.relayCnt(relayCnt)
			.user(user)
			.build();
		activityLogRepository.save(activityLog);

		return activityLog;
	}

	@Override
	public ActivityLogCommitResponse getActivityLogCommit(String userId) {
		var activityLogList = activityLogRepository.findByUserIdOrderByIdDesc(userId);
		Long count = 1L, relayCnt = 0L;
		for(ActivityLog activityLog : activityLogList){
			if(activityLog.getLastModifiedAt().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
				if(activityLog.getActivityType() == ActivityType.COMMIT) {
					count++;
					if(relayCnt == 0L) relayCnt = activityLog.getRelayCnt();
				}
			}else break;
		}

		var activityLogCommitResponse = ActivityLogCommitResponse.builder()
			.activityType(ActivityType.COMMIT)
			.relayCnt(relayCnt)
			.userId(userId)
			.todayCnt(count)
			.build();
		return activityLogCommitResponse;
	}

	@Transactional
	private Long findDate(String name){
		// 마지막 활동기록 조회
		var lastActivityLog = activityLogRepository.findTopByUserIdOrderByIdDesc(findUser(name).getId())
			.orElseThrow(NotFoundException::new);

		var past = lastActivityLog.getLastModifiedAt().getDayOfMonth();
		var present = LocalDateTime.now().getDayOfMonth();
		var relayCnt = lastActivityLog.getRelayCnt();

		// 날짜의 차이에 따라 relayCnt를 변화시킨 값을 return
		return switch (present - past) {
			case 0 -> relayCnt;
			case 1 -> {
				// 변화량이 있을 경우
				// send("activity-update", lastActivityLog);
				yield relayCnt + 1;
			}
			default -> 1L;
		};
	}

	public User findUser(String name){
		// user에서 찾아야합니다!
		var existingUser = userRepository.findByNickname(name).orElseThrow(NotFoundException::new);
		return existingUser;
	}
}
