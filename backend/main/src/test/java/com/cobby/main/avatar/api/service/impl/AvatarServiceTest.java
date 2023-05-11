package com.cobby.main.avatar.api.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cobby.main.avatar.api.controller.AvatarController;
import com.cobby.main.avatar.api.service.AvatarService;
import com.cobby.main.avatar.db.repository.AvatarRepository;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Avatar 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class AvatarServiceTest {

	@Mock
	private AvatarRepository avatarRepository;

	@MockBean
	private AvatarService avatarService;

	@BeforeEach
	void setUp() {
	}

	@Test
	void selectAvatar() {
	}

	@Test
	void insertDefaultAvatar() {
	}

	@Test
	void updateAvatar() {
	}

	@Test
	void resetAvatar() {
	}

	@Test
	void deleteAvatar() {
	}
}