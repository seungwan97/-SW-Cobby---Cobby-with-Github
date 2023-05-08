package com.cobby.main.user.api.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

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

	private final UserRepository userRepository;
	private final StatRepository statRepository;
	private static final String GITHUB_API_URL = "https://api.github.com";

	@Override
	public UserMainResponse getUserInfo(String userId) {
		var user = userRepository.findById(userId).orElseThrow(NotFoundException::new);


		var userMainResponse =UserMainResponse.builder()
			.nickname(user.getNickname())
			.build();
		return userMainResponse;
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
		var existingUser = userRepository.findById(userPostRequest.userId());
		if (existingUser.isPresent()) {
			var user1 = existingUser.orElseThrow(NotFoundException::new);
			var user = user1.toBuilder()
				.build();

			user = user.toBuilder()
				.state(State.X)
				.githubToken(userPostRequest.githubToken())
				.build();

			userRepository.save(user);

			var stat = statRepository.findById(userPostRequest.userId()).orElseThrow(NotFoundException::new);
			// 새로운 사용자의 통계 정보를 생성합니다.
			stat = stat.toBuilder()
				.commitCnt(getStatList(userPostRequest, 5))
				.starCnt(getStatList(userPostRequest, 3))
				.forkCnt(getFork(userPostRequest))
				.prCnt(getStatList(userPostRequest, 7))
				.followerCnt(getFollower(userPostRequest))
				.issueCnt(getStatList(userPostRequest, 9))
				.build();
			statRepository.save(stat);
		} else {
			// 기존 사용자가 존재하지 않는 경우, 새로운 사용자 정보를 생성합니다.
			User user = User.builder()
				.id(userPostRequest.userId())
				.nickname(userPostRequest.nickname())
				.state(State.A)
				.githubUrl(userPostRequest.githubUrl())
				.githubToken(userPostRequest.githubToken())
				.build();
			userRepository.save(user);

			var user1 = userRepository.findById(userPostRequest.userId()).orElseThrow(NotFoundException::new);

			// 새로운 사용자의 통계 정보를 생성합니다.
			Stat stat = Stat.builder()
				.user(user1)
				.commitCnt(getStatList(userPostRequest, 5))
				.starCnt(getStatList(userPostRequest, 3))
				.forkCnt(getFork(userPostRequest))
				.prCnt(getStatList(userPostRequest, 7))
				.followerCnt(getFollower(userPostRequest))
				.issueCnt(getStatList(userPostRequest, 9))
				.build();
			statRepository.save(stat);
		}
	}

		private Long getFork(UserPostRequest userPostRequest){
		OkHttpClient client = new OkHttpClient();
		Request.Builder builder = new Request.Builder()
			.url(GITHUB_API_URL + "/user" + "/repos")
			.addHeader("Authorization", "token " + userPostRequest.githubToken());
		Request request = builder.build();

		try {
			long beforeTime = System.currentTimeMillis();
			Response response = client.newCall(request).execute();
			if (response.code() == 200) {

				JSONArray repositories = new JSONArray(response.body().string());
				Long forkCnt = 0L, sum = 0L;

				for (int i = 0; i < repositories.length(); i++) {
					JSONObject repo = repositories.getJSONObject(i);
					forkCnt = repo.getLong("forks_count");
					sum += forkCnt;
				}
				long afterTime = System.currentTimeMillis();
				long secDiffTime = (afterTime - beforeTime)/1000;
				log.info("시간차이(m) : {}", secDiffTime);

				return sum;

			} else {
				System.out.println("API 요청이 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Long getFollower(UserPostRequest userPostRequest){
		OkHttpClient client = new OkHttpClient();
		Request.Builder builder = new Request.Builder()
			.url(GITHUB_API_URL + "/user")
			.addHeader("Authorization", "token " + userPostRequest.githubToken());
		Request request = builder.build();

		try {
			Response response = client.newCall(request).execute();
			if (response.code() == 200) {

				long beforeTime = System.currentTimeMillis();
				JSONObject jsonObject = new JSONObject(response.body().string());
				Long follower = 0L, sum = 0L;

				follower = jsonObject.getLong("followers");
				long afterTime = System.currentTimeMillis();
				long secDiffTime = (afterTime - beforeTime)/1000;
				log.info("시간차이(m) : {}", secDiffTime);
				return follower;

			} else {
				System.out.println("API 요청이 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
