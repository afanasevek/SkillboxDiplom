package ru.afanasev.diplom.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.authDtos.LoginDtoRequest;
import ru.afanasev.diplom.object.dto.authDtos.LoginSuccessDtoResponse;
import ru.afanasev.diplom.object.dto.userDtos.UserLoginDtoResponse;
import ru.afanasev.diplom.object.repository.UserRepository;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ApiAuthControllerTest {
	@Autowired
	private MockMvc mocMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		User user = new User();
		user.setId(1);
		user.setName("test_user");
		user.setEmail("test@test.com");
		user.setPassword("test111111");
		user.setIsModerator((byte) 1);
		user.setRegTime(LocalDateTime.now().minusMonths(4).minusDays(12));
		userRepository.save(user);
	}
	
	@Test
	void testLogin() throws JsonProcessingException, Exception {
		LoginDtoRequest request = new LoginDtoRequest();
		request.setE_mail("test@test.com");
		request.setPassword("test111111");
		
		LoginSuccessDtoResponse response = new LoginSuccessDtoResponse();
		response.setResult(true);
		UserLoginDtoResponse user = new UserLoginDtoResponse();
		user.setEmail("test@test.com");
		user.setId(1);
		user.setModeration(true);
		user.setModerationCount(0);
		user.setName("test_user");
		user.setSettings(true);
		user.setPhoto(null);
		response.setUser(user);
		
		mocMvc.perform(post("/api/auth/login") .contentType(MediaType.APPLICATION_JSON).content((objectMapper.writeValueAsString(request)))).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(objectMapper.writeValueAsString(response)));

	}
}
