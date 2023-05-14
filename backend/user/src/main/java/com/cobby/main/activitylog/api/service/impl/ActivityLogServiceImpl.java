package com.cobby.main.activitylog.api.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cobby.main.activitylog.api.dto.response.AttendanceLogResponse;
import com.cobby.main.activitylog.api.dto.response.CommitLogResponse;
import com.cobby.main.activitylog.api.service.ActivityLogService;
import com.cobby.main.activitylog.db.entity.ActivityLog;
import com.cobby.main.activitylog.db.entity.ActivityType;
import com.cobby.main.activitylog.db.repository.ActivityLogRepository;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.user.db.entity.User;
import com.cobby.main.user.db.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

	@Value("${kafka-producer.topics.update-exp}")
	private String KAFKA_TOPIC;
	private final UserRepository userRepository;
	private final ActivityLogRepository activityLogRepository;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	// 웹훅에 따라 사용자의 커밋 로그를 새로 저장하고, 연속 커밋 횟수도 업데이트 합니다.
	@Override
	public void webhookCreate(Map<String, String> headers, String payload) {

		for (Map.Entry<String, String> entry : headers.entrySet()) {

			JsonObject jsonObject = new Gson().fromJson(payload, JsonObject.class);
			if (entry.getValue().equals("push")) { // commit push면
				// ( commit: pusher )
				var nickname = jsonObject.getAsJsonObject("pusher").get("name").getAsString();

				// 해당 닉네임을 가진 사용자가 존재하는 지 확인
				var user = findUserByNickname(nickname);

				// 커밋 로그를 DB에 저장
				saveActivityLog(user, ActivityType.COMMIT);
			}
		}
	}

	// 사용자의 마지막 출석 기록을 조회하고, 연속 출석 횟수도 업데이트 합니다.
	@Override
	public AttendanceLogResponse getAttendanceActivityLog(String userId) {
		// 해당 사용자가 존재하는 사용자인지 체크
		var user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

		var activityLog = saveActivityLog(user, ActivityType.ATTENDANCE);

		return AttendanceLogResponse.builder()
			.activityLog(activityLog)
			.build();
	}

	// 사용자의 마지막 커밋 기록을 조회합니다.
	@Override
	public CommitLogResponse getCommitActivityLog(String userId) {
		// 해당 사용자가 존재하는 사용자인지 체크
		var user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

		// 사용자의 커밋 활동 로그들을 조회
		var activityLogs = activityLogRepository.findAllByType(userId, ActivityType.COMMIT);

		// 조회한 로그들에서 오늘 커밋 횟수를 계산
		var today = LocalDateTime.now().getDayOfMonth();
		var todayCnt = activityLogs.stream()
			.filter((log) ->
				today == log.getCreatedAt().getDayOfMonth())
			.toList()
			.size();

		// 사용자의 커밋 활동 로그 중 가장 최근 것을 조회
		var lastLog = activityLogs.get(0);

		return CommitLogResponse.builder()
			.activityLog(lastLog)
			.todayCnt((long)todayCnt)
			.build();
	}

	// 닉네임으로 사용자를 조회합니다.
	public User findUserByNickname(String name) {
		// user 에서 찾아야합니다!
		return userRepository.findByNickname(name).orElseThrow(NotFoundException::new);
	}

	// 사용자의 활동 타입 별 마지막 로그 하나를 조회합니다.
	private ActivityLog findLastActivityLogByType(User user, ActivityType type) {
		return activityLogRepository.findLastByType(user.getId(), type)
			.orElseThrow(NotFoundException::new);
	}

	// 사용자의 활동 타입 별 업데이트된 로그를 생성합니다.
	private ActivityLog saveActivityLog(User user, ActivityType type) {
		// 해당 사용자의 해당 type 마지막 ActivityLog 를 조회
		var lastLog = findLastActivityLogByType(user, type);

		// 마지막 로그의 수정 시간을 기반으로 relayCnt 업데이트
		var recentRelayCnt = lastLog.getRelayCnt();
		var currentRelayCnt = updateRelayCnt(lastLog.getCreatedAt(), lastLog.getRelayCnt());

		// 새로운 ActivityLog 를 DB에 저장
		var newLog = activityLogRepository.save(
			ActivityLog.builder()
				.user(user)
				.activityType(type)
				.relayCnt(currentRelayCnt)
				.build()
		);

		// relayCnt에 변화가 있었다면 main-service 에 메시지를 보냄
		if (!currentRelayCnt.equals(recentRelayCnt)) {
			sendActivityLog(newLog);
		}

		return newLog;
	}

	// 연속 횟수를 업데이트 합니다.
	private Long updateRelayCnt(LocalDateTime recent, Long relayCnt) {
		var past = recent.getDayOfMonth();
		var now = LocalDateTime.now().getDayOfMonth();

		// 날짜의 차이에 따라 연속 횟수를 업데이트
		return switch (now - past) {
			case 0 ->  // 같은 날짜일 경우
				relayCnt;
			case 1 ->  // 바로 다음날일 경우
				relayCnt + 1;
			default -> // 그 이상 차이날 경우
				1L;
		};
	}

	// kafka 메시지를 보냅니다.
	private void sendActivityLog(ActivityLog activityLog) {
		String jsonInString = "";
		var message = Map.of(
			"userId", activityLog.getUser().getId(),
			"type", activityLog.getActivityType().name()
		);

		try {
			jsonInString = objectMapper.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		kafkaTemplate.send(KAFKA_TOPIC, jsonInString);
		log.info("Produced message for [\"" + KAFKA_TOPIC + "\"] topic at " + LocalDateTime.now());
	}
}
