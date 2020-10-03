package ru.afanasev.diplom.service;

import org.springframework.transaction.annotation.Transactional;

import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.authDtos.LoginDtoRequest;

public interface AuthService {
	@Transactional
	String getCaptcha(String secretv2) throws Exception;

	User postLogin(LoginDtoRequest request);
}
