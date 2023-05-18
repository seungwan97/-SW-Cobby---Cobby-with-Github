// package com.cobby.main.costume;
//
// import java.util.UUID;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.DisplayNameGeneration;
// import org.junit.jupiter.api.DisplayNameGenerator;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MockMvcBuilder;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.filter.CharacterEncodingFilter;
//
// import com.cobby.main.common.exception.handler.GlobalExceptionHandler;
// import com.cobby.main.costume.api.controller.CostumeController;
// import com.cobby.main.costume.api.service.CostumeService;
//
// @DisplayName("Costume 컨트롤러 테스트")
// @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
// @ExtendWith(SpringExtension.class)
// @WebMvcTest(CostumeController.class)
// public class CostumeControllerTest {
//
// 	private MockMvc mvc;
//
// 	@MockBean private CostumeService costumeService;
//
// 	@BeforeEach
// 	public void setMvc() {
// 		this.mvc = MockMvcBuilders.standaloneSetup(new CostumeController(costumeService))
// 			.addFilters(new CharacterEncodingFilter("UTF-8", true))
// 			.build();
// 	}

	// @Test
	// public void UUID_유효성_테스트 () {
	// 	var uuid = UUID.randomUUID();
	// 	System.out.println(uuid);
	// }
// }
