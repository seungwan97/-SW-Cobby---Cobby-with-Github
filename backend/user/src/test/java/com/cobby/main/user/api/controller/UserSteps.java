package com.cobby.main.user.api.controller;

import org.springframework.http.MediaType;

import com.cobby.main.user.db.entity.State;
import com.cobby.main.user.db.entity.User;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class UserSteps {

	public static ExtractableResponse<Response> 유저등록요청(User request) {
		return RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(유저등록요청_생성())
			.when()
			.post("/api/users")
			.then()
			.log().all().extract();
	}

	public static User 유저등록요청_생성() {
		final String id = "userid";
		final String nickname = "s-sggul";
		final State state = State.A;
		final String github_uri = "uri";
		return new User(id, nickname, state, github_uri);
	}

	public static ExtractableResponse<Response> 유저조회요청(final String userId) {
		return RestAssured.given().log().all()
			.when()
			.get("/api/users/{userId}", userId)
			.then().log().all()
			.extract();
	}

	public static User 유저수정요청_생성() {
		return new User("userid", "s-sggul", State.X, "uri");
	}

	public static ExtractableResponse<Response> 유저수정요청(final String userId) {
		return RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(유저수정요청_생성())
			.when()
			.patch("/api/users/{userId}", userId)
			.then().log().all()
			.extract();
	}
}
