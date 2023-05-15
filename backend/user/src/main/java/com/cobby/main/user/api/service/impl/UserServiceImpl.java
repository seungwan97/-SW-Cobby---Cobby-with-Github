package com.cobby.main.user.api.service.impl;

import java.io.IOException;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.json.JSONObject;

import com.cobby.main.activitylog.db.entity.ActivityLog;
import com.cobby.main.activitylog.db.entity.ActivityType;
import com.cobby.main.activitylog.db.repository.ActivityLogRepository;
import com.cobby.main.common.exception.BaseRuntimeException;
import com.cobby.main.common.exception.NotFoundException;
import com.cobby.main.stat.db.entity.Stat;
import com.cobby.main.stat.db.repository.StatRepository;
import com.cobby.main.user.api.dto.request.UserPostRequest;
import com.cobby.main.user.api.dto.response.UserMainResponse;
import com.cobby.main.user.api.service.UserService;
import com.cobby.main.user.db.entity.State;
import com.cobby.main.user.db.entity.User;
import com.cobby.main.user.db.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String GITHUB_API_URL = "https://api.github.com";

	@Value("${kafka-producer.topics.make-avatar}")
	private String KAFKA_TOPIC;

	private final UserRepository userRepository;

	private final StatRepository statRepository;

	private final ActivityLogRepository activityLogRepository;

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public UserMainResponse getUserInfo(String userId) {
		var user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

		return UserMainResponse.builder()
			.nickname(user.getNickname())
			.githubUrl(user.getGithubUrl())
			.build();
	}

	@Override
	public void signOutUserInfo(String userId) {
		var user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

		user = user.toBuilder()
			.state(State.X)
			.build();

		userRepository.save(user);
	}

	@Override
	public void logInUserInfo(UserPostRequest userPostRequest) {
		// userId로 기존 사용자를 조회합니다.
		userRepository.findById(userPostRequest.userId()).ifPresentOrElse(
			// 존재하는 사용자일 경우
			existingUser -> {
				// 이미 존재하는 사용자의 state 를 활설화(A) 상태로 변경하고 다시 받은 github 토큰값과 함꼐 업데이트합니다.
				existingUser = existingUser.toBuilder()
					.state(State.A)
					.githubToken(userPostRequest.githubToken())
					.build();

				userRepository.save(existingUser);

				var stat = statRepository.findById(userPostRequest.userId())
					.orElseThrow(NotFoundException::new);

				// 새로운 사용자의 통계 정보를 생성합니다.
				stat = stat.toBuilder()
					.commitCnt(getStatList(userPostRequest, 5))
					.starCnt(getStatList(userPostRequest, 3))
					.prCnt(getStatList(userPostRequest, 7))
					.followerCnt(getFollower(userPostRequest))
					.issueCnt(getStatList(userPostRequest, 9))
					.build();
				statRepository.save(stat);
			},

			// 기존 사용자가 존재하지 않는 경우
			() -> {
				// 새로운 사용자 정보를 생성합니다.
				var user = User.builder()
					.id(userPostRequest.userId())
					.nickname(userPostRequest.nickname())
					.state(State.A)
					.githubUrl(userPostRequest.githubUrl())
					.githubToken(userPostRequest.githubToken())
					.build();
				userRepository.save(user);

				var user1 = userRepository.findById(userPostRequest.userId()).orElseThrow(NotFoundException::new);

				// 새로운 사용자의 통계 정보를 생성합니다.
				var stat = Stat.builder()
					.user(user1)
					.commitCnt(getStatList(userPostRequest, 5))
					.starCnt(getStatList(userPostRequest, 3))
					.prCnt(getStatList(userPostRequest, 7))
					.followerCnt(/*getFollower(userPostRequest)*/0L)
					.issueCnt(getStatList(userPostRequest, 9))
					.build();

				// attendance 기록 하나 쌓기
				activityLogRepository.save(
					ActivityLog.builder()
						.user(user)
						.activityType(ActivityType.ATTENDANCE)
						.relayCnt(1L)
						.build()
				);

				// commit 기록 하나 쌓기
				activityLogRepository.save(
					ActivityLog.builder()
						.user(user)
						.activityType(ActivityType.COMMIT)
						.relayCnt(0L)
						.build()
				);

				statRepository.save(stat);

				// // 이후 user 정보를 메시지 큐에 보냅니다.
				// var res = sendUserId(user.getId());
				// log.info("Sending message: " + res);
			});
	}

	private String sendUserId(String id) {
		kafkaTemplate.send(KAFKA_TOPIC, id);
		return id;
	}

	private Long getFollower(UserPostRequest userPostRequest) {
		OkHttpClient client = new OkHttpClient();
		Request.Builder builder = new Request.Builder()
			.url(GITHUB_API_URL + "/user")
			.addHeader("Authorization", "token " + userPostRequest.githubToken());
		Request request = builder.build();

		try {
			Response response = client.newCall(request).execute();
			if (response.code() == 200) {

				JSONObject jsonObject = new JSONObject(response.body().string());
				Long follower = 0L, sum = 0L;

				follower = jsonObject.getLong("followers");
				return follower;

			} else {
				System.out.println("API 요청이 실패했습니다.");
			}
		} catch (Exception e) {
			throw new BaseRuntimeException(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "Github API 정보를 얻어오는 데 실패했습니다.");
		}
		return null;
	}

	private long getStatList(UserPostRequest userPostRequest, int crawling) {

		StringBuilder gitStat = new StringBuilder();
		gitStat.append("https://github-readme-stats.vercel.app/api?username=")
			.append(userPostRequest.nickname())
			.append("&count_private=true");
		Document doc;
		String text = null;

		try {
			String url = String.valueOf(gitStat);
			String parser = XMLResourceDescriptor.getXMLParserClassName();
			SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);

			doc = factory.createDocument(url);

			text = doc.getElementsByTagName("text").item(crawling).getTextContent();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return Long.parseLong(text);
	}

}
