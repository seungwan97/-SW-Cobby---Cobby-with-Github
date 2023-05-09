package com.cobby.main.activitylog.api.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

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
			// System.out.println("key: " + entry.getKey());

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




		// log 쌓기 생성하면됩니다.
	}

	public Long findDate(String name){
		var yesterday = activityLogRepository.findByUserIdOrderByRelayCntDesc(findUser(name).getId());
		System.out.println(yesterday);

		// date time에서 날 확인해서 검사하는 거 해야합니다.
		return 0L;
	}

	public User findUser(String name){
		// user에서 찾아야합니다!
		var existingUser = userRepository.findByNickname(name).orElseThrow(NotFoundException::new);
		return existingUser;
	}
}
