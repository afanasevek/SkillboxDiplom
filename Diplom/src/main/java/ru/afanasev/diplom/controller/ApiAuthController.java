package ru.afanasev.diplom.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.authDtos.CaptchaDtoResponse;
import ru.afanasev.diplom.object.dto.authDtos.LoginDtoRequest;
import ru.afanasev.diplom.object.dto.authDtos.LoginDtoResponse;
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

	
	public ApiAuthController(AuthService captchaService, PostService postService) {
		this.captchaService = captchaService;
		this.postService = postService;
	}



	@GetMapping("api/auth/captcha")
	public CaptchaDtoResponse getCaptcha() throws Exception {
		String secret = Utils.generateUUID();
		
		return CaptchaMapper.getCaptchaDtoResponse(captchaService.getCaptcha(secret), secret);
	}
	@PostMapping("/api/auth/login")
	public LoginDtoResponse postLogin(LoginDtoRequest login) {
		System.out.println(1);
		User user = captchaService.postLogin(login);
		System.out.println(user);
		UserLoginDtoResponse userDto = UserMapper.entityToUserLoginDtoResponse(user, postService.getModerationCountPost());
		
		return LoginMapper.getLoginDtoRespose(userDto);
		
		
	}
	
}
