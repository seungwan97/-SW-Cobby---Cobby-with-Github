package com.cobby.main.avatar.api.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
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

import com.cobby.main.avatar.api.dto.request.AvatarItemPostRequest;
import com.cobby.main.avatar.api.dto.response.AvatarCostumeGetResponse;
import com.cobby.main.avatar.api.dto.response.AvatarQuestGetResponse;
import com.cobby.main.avatar.api.dto.response.AvatarTitleGetResponse;
import com.cobby.main.avatar.api.service.AvatarCostumeService;
import com.cobby.main.avatar.api.service.AvatarQuestService;
import com.cobby.main.avatar.api.service.AvatarTitleService;
import com.cobby.main.avatar.db.entity.Avatar;
import com.cobby.main.costume.db.entity.Costume;
import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.quest.db.entity.Quest;
import com.cobby.main.quest.db.entity.enumtype.QuestCategory;
import com.cobby.main.title.db.entity.Title;

@SuppressWarnings("NonAsciiCharacters")
@DisplayName("Avatar Item 컨트롤러 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(AvatarItemController.class)
class AvatarItemControllerTest {

	private MockMvc mvc;

	private String testUserId = UUID.randomUUID().toString();

	private Avatar dummyAvatar;

	private Costume dummyCostume;

	private Title dummyTitle;

	private Quest dummyQuest;

	@MockBean
	private AvatarCostumeService avatarCostumeService;

	@MockBean
	private AvatarTitleService avatarTitleService;

	@MockBean
	private AvatarQuestService avatarQuestService;

	@BeforeEach
	public void setMvc() {
		this.mvc = MockMvcBuilders.standaloneSetup(
				new AvatarItemController(avatarCostumeService, avatarTitleService, avatarQuestService))
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();

		this.dummyAvatar = Avatar.builder()
			.avatarId(testUserId)
			.level(1)
			.exp(0)
			.currentCostumes("[1, 2, 3]")
			.build();

		this.dummyQuest = Quest.builder()
			.questId(1L)
			.questName("quest1")
			.questType(QuestCategory.COMMIT)
			.questGoal(10)
			.costumes(List.of(Costume.builder()
				.costumeId(1L)
				.quest(dummyQuest)
				.name("name")
				.category(CostumeCategory.FACE)
				.imgUrl("imgUrl")
				.silhouetteImgUrl("silhouertteImgUrl")
				.gifUrl("gifUrl")
				.build()))
			.titles(List.of(Title.builder()
				.titleId(1L)
				.name("title1")
				.quest(dummyQuest)
				.explanation("This is title1")
				.build()))
			.build();

		this.dummyCostume = Costume.builder()
			.costumeId(1L)
			.quest(dummyQuest)
			.name("name")
			.category(CostumeCategory.FACE)
			.imgUrl("imgUrl")
			.silhouetteImgUrl("silhouertteImgUrl")
			.gifUrl("gifUrl")
			.build();

		this.dummyTitle = Title.builder()
			.titleId(1L)
			.name("title1")
			.quest(dummyQuest)
			.explanation("This is title1")
			.build();
	}

	@Test
	public void AvatarItem_타입_별_조회_테스트() throws Exception {
		// costume 타입 조회 테스트
		// given
		given(avatarCostumeService.selectItem(testUserId, 1L))
			.willReturn(
				AvatarCostumeGetResponse.builder()
					.avatarCostumeId(1L)
					.costume(dummyCostume)
					.build());

		// when
		final ResultActions getCostumeActions = mvc.perform(
			get("/api/avatars/inventories/costume/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		// then
		getCostumeActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value("200"))
			.andExpect(jsonPath("title").value("OK"));

		// title 타입 조회 테스트
		// given
		given(avatarTitleService.selectItem(testUserId, 1L))
			.willReturn(
				AvatarTitleGetResponse.builder()
					.avatarTitleId(1L)
					.title(dummyTitle)
					.build());

		// when
		final ResultActions getTitleActions = mvc.perform(
			get("/api/avatars/inventories/title/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		// then
		getTitleActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value("200"))
			.andExpect(jsonPath("title").value("OK"));

		// quest 타입 조회 테스트
		// given
		given(avatarQuestService.selectItem(testUserId, 1L))
			.willReturn(
				AvatarQuestGetResponse.builder()
					.avatarQuestId(1L)
					.quest(dummyQuest)
					.build());

		// when
		final ResultActions getQuestActions = mvc.perform(
			get("/api/avatars/inventories/quest/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		// then
		getQuestActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value("200"))
			.andExpect(jsonPath("title").value("OK"));
	}

	@Test
	public void AvatarItem_타입_별_전체_조회_테스트() throws Exception {
		// costume 타입 조회 테스트
		// given
		given(avatarCostumeService.selectAllItems(testUserId))
			.willReturn(
				List.of(AvatarCostumeGetResponse.builder()
					.avatarCostumeId(1L)
					.costume(dummyCostume)
					.build()));

		// when
		final ResultActions getCostumeActions = mvc.perform(
			get("/api/avatars/inventories/costume")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		// then
		getCostumeActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value("200"))
			.andExpect(jsonPath("title").value("OK"));

		// title 타입 조회 테스트
		// given
		given(avatarTitleService.selectAllItems(testUserId))
			.willReturn(
				List.of(AvatarTitleGetResponse.builder()
					.avatarTitleId(1L)
					.title(dummyTitle)
					.build()));

		// when
		final ResultActions getTitleActions = mvc.perform(
			get("/api/avatars/inventories/title")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		// then
		getTitleActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value("200"))
			.andExpect(jsonPath("title").value("OK"));

		// quest 타입 조회 테스트
		// given
		given(avatarQuestService.selectAllItems(testUserId))
			.willReturn(
				List.of(AvatarQuestGetResponse.builder()
					.avatarQuestId(1L)
					.quest(dummyQuest)
					.build()));

		// when
		final ResultActions getQuestActions = mvc.perform(
			get("/api/avatars/inventories/quest")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"));

		// then
		getQuestActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value("200"))
			.andExpect(jsonPath("title").value("OK"));
	}

	@Test
	public void AvatarItem_타입_별_생성_테스트() throws Exception {
		// costume 생성 테스트
		// given
		given(avatarCostumeService.insertItem(testUserId, new AvatarItemPostRequest("costume", 1L)))
			.willReturn(1L);

		// when
		final ResultActions getCostumeActions = mvc.perform(
			post("/api/avatars/inventories")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(
					"{" +
					"\"itemType\": \"costume\"," +
					"\"itemId\": 1" +
					"}"
				));

		// then
		getCostumeActions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("status").value("201"))
			.andExpect(jsonPath("title").value("created"))
			.andExpect(jsonPath("content").value("보유 아이템을 성공적으로 추가했습니다. (type=costume, ID=1)"));

		// title 생성 테스트
		// given
		given(avatarTitleService.insertItem(testUserId, new AvatarItemPostRequest("title", 1L)))
			.willReturn(1L);

		// when
		final ResultActions getTitleActions = mvc.perform(
			post("/api/avatars/inventories")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(
					"{" +
						"\"itemType\": \"title\"," +
						"\"itemId\": 1" +
						"}"
				));

		// then
		getTitleActions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("status").value("201"))
			.andExpect(jsonPath("title").value("created"))
			.andExpect(jsonPath("content").value("보유 아이템을 성공적으로 추가했습니다. (type=title, ID=1)"));

		// costume 생성 테스트
		// given
		given(avatarQuestService.insertItem(testUserId, new AvatarItemPostRequest("quest", 1L)))
			.willReturn(1L);

		// when
		final ResultActions getQuestActions = mvc.perform(
			post("/api/avatars/inventories")
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", testUserId)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(
					"{" +
						"\"itemType\": \"quest\"," +
						"\"itemId\": 1" +
						"}"
				));

		// then
		getQuestActions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("status").value("201"))
			.andExpect(jsonPath("title").value("created"))
			.andExpect(jsonPath("content").value("보유 아이템을 성공적으로 추가했습니다. (type=quest, ID=1)"));
	}

}