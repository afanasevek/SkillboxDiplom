package ru.afanasev.diplom.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import liquibase.pro.packaged.iF;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.authDtos.CaptchaDtoResponse;
import ru.afanasev.diplom.object.dto.authDtos.LoginDtoRequest;
import ru.afanasev.diplom.object.dto.authDtos.LoginDtoResponse;
import ru.afanasev.diplom.object.dto.authDtos.LoginErrorDtoResponse;
import ru.afanasev.diplom.object.dto.mapper.CaptchaMapper;
import ru.afanasev.diplom.object.dto.mapper.LoginMapper;
import ru.afanasev.diplom.object.dto.mapper.UserMapper;
import ru.afanasev.diplom.object.dto.userDtos.UserLoginDtoResponse;
import ru.afanasev.diplom.service.AuthService;
import ru.afanasev.diplom.service.PostService;
import ru.afanasev.diplom.service.Utils;

@RestController
public class ApiAuthController {

	private final AuthService captchaService;
	private final PostService postService;
	private final static Map<String, Integer> USERS = new HashMap<>();
	private final AuthenticationManager authenticationManager;

	public ApiAuthController(AuthService captchaService, PostService postService, AuthenticationManager authenticationManager) {
		this.captchaService = captchaService;
		this.postService = postService;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("api/auth/captcha")
	public CaptchaDtoResponse getCaptcha() throws Exception {
		String secret = Utils.generateUUID();

		return CaptchaMapper.getCaptchaDtoResponse(captchaService.getCaptcha(secret), secret);
	}

	@PostMapping("/api/auth/login")
	public LoginDtoResponse login(@RequestBody LoginDtoRequest login) {
		
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getE_mail(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		User user = captchaService.postLogin(login);
		if (user != null) {
			USERS.put(user.getEmail(), user.getId());
		}

		UserLoginDtoResponse userDto = UserMapper.entityToUserLoginDtoResponse(user,
				postService.getModerationCountPost());

		return LoginMapper.getLoginDtoRespose(userDto);

	}

	@GetMapping("api/auth/check")
	public LoginDtoResponse authCheck(@AuthenticationPrincipal User user) {
		if (user != null) {

			if (!USERS.containsValue(user.getId())) {
				LoginErrorDtoResponse error = new LoginErrorDtoResponse();
				error.setResult(false);
				return error;
			}
			UserLoginDtoResponse userDto = UserMapper.entityToUserLoginDtoResponse(user,
					postService.getModerationCountPost());

			return LoginMapper.getLoginDtoRespose(userDto);
			
		} else {

			LoginErrorDtoResponse error = new LoginErrorDtoResponse();
			error.setResult(false);
			return error;

		}

	}

}
