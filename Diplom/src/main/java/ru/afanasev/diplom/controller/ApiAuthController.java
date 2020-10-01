package ru.afanasev.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.authDtos.CaptchaDtoResponse;
import ru.afanasev.diplom.object.dto.authDtos.LoginDtoRequest;
import ru.afanasev.diplom.object.dto.authDtos.LoginDtoResponse;
import ru.afanasev.diplom.object.dto.mapper.CaptchaMapper;
import ru.afanasev.diplom.service.AuthService;
import ru.afanasev.diplom.service.Utils;



@RestController
public class ApiAuthController {
	
	private final AuthService captchaService;
	
	
	
	public ApiAuthController(AuthService captchaService) {
		super();
		this.captchaService = captchaService;
	}



	@GetMapping("api/auth/captcha")
	public CaptchaDtoResponse getCaptcha() throws Exception {
		String secret = Utils.generateUUID();
		
		return CaptchaMapper.getCaptchaDtoResponse(captchaService.getCaptcha(secret), secret);
	}
	@PostMapping("/api/auth/login")
	public LoginDtoResponse postLogin(LoginDtoRequest login) {
		User user = captchaService.postLogin(login);
		
	}
	
}
