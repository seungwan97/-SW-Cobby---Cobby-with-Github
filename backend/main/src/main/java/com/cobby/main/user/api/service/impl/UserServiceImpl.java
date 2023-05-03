package com.cobby.main.user.api.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
		var stat = statRepository.findById(userId).orElseThrow(NotFoundException::new);


		var userMainResponse =UserMainResponse.builder()
			.nickname(user.getNickname())
			.commitCnt(stat.getCommitCnt().intValue())
			.starCnt(stat.getStarCnt().intValue())
			.forkCnt(stat.getForkCnt().intValue())
			.build();
		return userMainResponse;
	}

	@Override
	public void signOutUserInfo(String userId) {
		var user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
		log.info(user.toString());
		log.info(String.valueOf(user.getLastModifiedAt()));

		user = user.toBuilder()
			.state(State.X)
			.build();

		log.info(user.toString());

		userRepository.save(user);
	}

	@Override
	public void logInUserInfo(UserPostRequest userPostRequest) {

		// 유저는 일단 아마도 끝
		var user = User.builder()
			.id(userPostRequest.userId())
			.nickname(userPostRequest.nickname())
			.state(State.A)
			.githubUrl(userPostRequest.githubUrl())
			.githubToken(userPostRequest.githubToken())
			.build();

		// var stat = bulidStat(userPostRequest);
		userRepository.save(user);

		var user1 = userRepository.findById(userPostRequest.userId()).orElseThrow(NotFoundException::new);



		var stat = Stat.builder()
			.user(user1)
			.commitCnt(getGitStatList(userPostRequest, 5))
			.starCnt(getGitStatList(userPostRequest, 3))
			.forkCnt(getFork(userPostRequest))
			.prCnt(getGitStatList(userPostRequest, 7))
			.followerCnt(getFollower(userPostRequest))
			.issueCnt(getGitStatList(userPostRequest, 9))
			.build();


		statRepository.save(stat);
	}

	private Long getFork(UserPostRequest userPostRequest){
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(GITHUB_API_URL + "/user" + "/repos"))
			.header("Authorization", "token " + userPostRequest.githubToken())
			.build();
		try {
			long beforeTime = System.currentTimeMillis();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {

				JSONArray repositories = new JSONArray(response.body());
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
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(GITHUB_API_URL + "/user"))
			.header("Authorization", "token " + userPostRequest.githubToken())
			.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {

				long beforeTime = System.currentTimeMillis();
				JSONObject jsonObject = new JSONObject(response.body());
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

	private long getGitStatList(UserPostRequest userPostRequest, int crawling) {

		StringBuilder gitStat = new StringBuilder();
		gitStat.append("https://github-readme-stats.vercel.app/api?username=")
			.append(userPostRequest.nickname())
			.append("&count_private=true");
		Document doc;
		String commitsText = null;

		try {
			String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
			String url = String.valueOf(gitStat);
			String parser = XMLResourceDescriptor.getXMLParserClassName();
			SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);

			doc = factory.createDocument(url);

			commitsText = doc.getElementsByTagName("text").item(crawling).getTextContent();
			System.out.println("Commits: " + commitsText);


		} catch (IOException e) {
			e.printStackTrace();
		}

		return Long.parseLong(commitsText);
	}

}
