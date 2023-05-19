package com.cobby.main.common.utils.github;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cobby.main.stat.api.dto.response.StatResponse;
import com.cobby.main.stat.db.repository.StatRepository;

public class BaseGitHubTest {
	private static final String GITHUB_API_URL = "https://api.github.com";

	private static RestTemplate restTemplate = new RestTemplate();
	private static String githubToken = "token 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";
	private static StatRepository statRepository;

	public static int getCommitCount(String owner, String repo) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + githubToken);
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(GITHUB_API_URL + "/repos/" + owner + "/" + repo));

		ResponseEntity<StatResponse> response = restTemplate.exchange(request, StatResponse.class);
		return response.getBody().getCommitCnt();
	}

	public static void main(String[] args) {
		int a = BaseGitHubTest.getCommitCount("daeeun1", "2022_04_Article_Manager");
		System.out.println(a);
	}
}