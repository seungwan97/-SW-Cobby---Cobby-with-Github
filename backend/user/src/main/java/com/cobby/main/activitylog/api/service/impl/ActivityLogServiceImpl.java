package com.cobby.main.activitylog.api.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cobby.main.activitylog.api.dto.response.ActivityLogCommitResponse;
import com.cobby.main.activitylog.api.dto.response.ActivityLogResponse;
import com.cobby.main.activitylog.api.service.ActivityLogService;
import com.cobby.main.activitylog.db.entity.ActivityLog;
import com.cobby.main.activitylog.db.entity.ActivityType;
import com.cobby.main.activitylog.db.repository.ActivityLogRepository;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.user.db.entity.User;
import com.cobby.main.user.db.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

	private final UserRepository userRepository;
	private final ActivityLogRepository activityLogRepository;

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

	@Override
	public ActivityLogResponse getactivityLogInfo(String userId) {
		var existingActivityLog = activityLogRepository.findTopByUserIdOrderByIdDesc(userId);
		ActivityLogResponse activityLogResponse;
		if (existingActivityLog.isPresent()) {
			var activityLog = existingActivityLog.orElseThrow(NotFoundException::new);

			activityLogResponse = ActivityLogResponse.builder()
				.activityType(ActivityType.ATTENDANCE)
				.relayCnt(findDate(activityLog.getUser().getNickname()))
				.userId(userId)
				.build();
		}else {
			activityLogResponse = ActivityLogResponse.builder()
				.activityType(ActivityType.ATTENDANCE)
				.relayCnt(1L)
				.userId(userId)
				.build();
		}
		var user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

		var activityLog = ActivityLog.builder()
			.activityType(activityLogResponse.getActivityType())
			.relayCnt(activityLogResponse.getRelayCnt())
			.user(user)
			.build();
		activityLogRepository.save(activityLog);

		return activityLogResponse;
	}

	@Override
	public ActivityLogCommitResponse getactivityLogCommit(String userId) {
		var existingActivityLog = activityLogRepository.findTopByUserIdOrderByIdDesc(userId).orElseThrow(NotFoundException::new);
		var activityLogList = activityLogRepository.findByUserIdOrderByIdDesc(userId);
		Long count = 1L;
		for(ActivityLog activityLog : activityLogList){
			if(activityLog.getLastModifiedAt().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
				if(activityLog.getActivityType() == ActivityType.COMMIT) count++;
			}else break;
		}

		var activityLogCommitResponse = ActivityLogCommitResponse.builder()
			.activityType(ActivityType.COMMIT)
			.relayCnt(existingActivityLog.getRelayCnt())
			.userId(userId)
			.todayCnt(count)
			.build();
		return activityLogCommitResponse;
	}

	public Long findDate(String name){
		var existingActivityLog = activityLogRepository.findTopByUserIdOrderByIdDesc(findUser(name).getId());
		if (existingActivityLog.isPresent()) {
			var lastUpdateDay = existingActivityLog.orElseThrow(NotFoundException::new);
			var yesterday = lastUpdateDay.getLastModifiedAt().getDayOfMonth();
			var today = LocalDateTime.now().getDayOfMonth();

			var activityLog = lastUpdateDay;
			// 결과
			if(yesterday == today) {
				return activityLog.getRelayCnt();
			}
			else if (yesterday + 1 == today) {
				return activityLog.getRelayCnt()+1;
			}
		}

		return 1L;
	}

	public User findUser(String name){
		// user에서 찾아야합니다!
		var existingUser = userRepository.findByNickname(name).orElseThrow(NotFoundException::new);
		return existingUser;
	}
}
