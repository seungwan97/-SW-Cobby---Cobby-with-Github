package com.cobby.main.avatar.api.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.cobby.main.avatar.api.dto.request.AvatarPatchRequest;
import com.cobby.main.avatar.api.dto.response.AvatarGetResponse;
import com.cobby.main.avatar.api.service.AvatarItemService;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.avatar.db.entity.Avatar;

@SuppressWarnings("NonAsciiCharacters")
@DisplayName("Avatar 컨트롤러 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(AvatarController.class)
class AvatarControllerTest {

	private MockMvc mvc;

	private String testUserId = UUID.randomUUID().toString();

	private Avatar dummy;

	@MockBean
	private AvatarService avatarService;

	@MockBean
	private AvatarItemService avatarCostumeService;

	@BeforeEach
	public void setMvc() {
		this.mvc = MockMvcBuilders.standaloneSetup(new AvatarController(avatarService))
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();

		this.dummy = Avatar.builder()
			.avatarId(testUserId)
			.level(1)
			.exp(0)
			.currentCostumes("[1, 2, 3]")
			.build();
	}

	@Test
	public void 기본_Avatar_생성_테스트() throws Exception {

		// given
		given(avatarService.insertDefaultAvatar(any()))
			.willReturn(
				dummy.getAvatarId());

		// when
		final ResultActions actions = mvc.perform(
			post("/api/avatars/")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		// then
		actions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("status").value("201"))
			.andExpect(jsonPath("title").value("created"))
			.andExpect(jsonPath("content").value("기본 아바타를 성공적으로 생성했습니다. (ID=" + testUserId + ")"));
	}

	@Test
	void Avatar_조회_테스트() throws Exception {

		// given
		given(avatarService.selectAvatar(any()))
			.willReturn(
				AvatarGetResponse.builder()
					.avatar(dummy)
					.build());

		final ResultActions actions = mvc.perform(
			get("/api/avatars/")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value(200))
			.andExpect(jsonPath("title").value("OK"));
		// content 내부 내용도 추가해야 함
	}

	@Test
	void Avatar_level_exp_모두_수정_테스트() throws Exception {

		// given
		var testAvatarUpdateInfo = Map.of("level", 35, "exp", 12345);

		given(avatarService.updateAvatar(testUserId, null))
			.willReturn(
				Avatar.builder()
					.avatarId(testUserId)
					.level(35)
					.exp(12345)
					.currentCostumes("[1,2,3]")
					.build()
					.getAvatarId());

		// when
		final ResultActions actions = mvc.perform(
			get("/api/avatars")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content("{" +
						"  \"level\" : 35, " +
						"  \"exp\" : 12345 " +
						"}"
				));

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value(200))
			.andExpect(jsonPath("title").value("OK"));
	}

	@Test
	void Avatar_level_만_수정_테스트() throws Exception {

		// given
		var testAvatarUpdateInfo = AvatarPatchRequest.builder()
			.level(35)
			.exp(12345)
			.face(2L)
			.body(3L)
			.build();

		given(avatarService.updateAvatar(testUserId, testAvatarUpdateInfo))
			.willReturn(
				Avatar.builder()
					.avatarId(testUserId)
					.level(35)
					.exp(12345)
					.currentCostumes("[1,2,3]")
					.build()
					.getAvatarId());

		// when
		final ResultActions actions = mvc.perform(
			get("/api/avatars")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content("{" +
					"  \"level\" : 35 " +
					"}"
				));

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value(200))
			.andExpect(jsonPath("title").value("OK"));
	}

	@Test
	void Avatar_exp_만_수정_테스트() throws Exception {

		// given
		var testAvatarUpdateInfo = AvatarPatchRequest.builder()
			.exp(12345)
			.build();

		given(avatarService.updateAvatar(testUserId, testAvatarUpdateInfo))
			.willReturn(
				Avatar.builder()
					.avatarId(testUserId)
					.level(35)
					.exp(12345)
					.currentCostumes("[1,2,3]")
					.build()
					.getAvatarId());

		// when
		final ResultActions actions = mvc.perform(
			get("/api/avatars")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content("{" +
					"  \"exp\" : 12345 " +
					"}"
				));

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value(200))
			.andExpect(jsonPath("title").value("OK"));
	}

	@Test
	void Avatar_초기화_테스트() throws Exception {
		// given
		given(avatarService.resetAvatar(any()))
			.willReturn(
				dummy.getAvatarId());

		// when
		final ResultActions actions = mvc.perform(
			get("/api/avatars/reset")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value(200))
			.andExpect(jsonPath("title").value("OK"));
	}

	@Test
	void Avatar_삭제_테스트() throws Exception {

		// given
		given(avatarService.deleteAvatar(testUserId))
			.willReturn(dummy.getAvatarId());

		// when
		final ResultActions actions = mvc.perform(
			delete("/api/avatars")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value(200))
			.andExpect(jsonPath("title").value("OK"));
	}

}