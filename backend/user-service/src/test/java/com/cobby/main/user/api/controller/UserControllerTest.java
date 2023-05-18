package com.cobby.main.user.api.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.cobby.main.ApiTest;
import com.cobby.main.user.db.repository.UserRepository;

class UserControllerTest extends ApiTest {

	@Autowired
	UserRepository userRepository;

	@Test
	void 유저등록(){
		final var request = UserSteps.유저등록요청_생성();

		final var response = UserSteps.유저등록요청(request);

		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	// @Test
	// void 유저조회() {
	// 	UserSteps.유저등록요청(UserSteps.유저등록요청_생성());
	// 	String id = "userid";
	//
	// 	final var response = UserSteps.유저조회요청(id);
	//
	// 	assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	// 	assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
	// }

	// @Test
	// void 상품수정() {
	// 	UserSteps.유저등록요청(UserSteps.유저등록요청_생성());
	// 	final String id = "userid";
	//
	// 	final var response = UserSteps.유저수정요청(id);
	//
	// 	assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	// 	assertThat(userRepository.findById(id));
	// }
}