package ru.afanasev.diplom.object.dto.mapper;

import ru.afanasev.diplom.object.dto.authDtos.CaptchaDtoResponse;

public class CaptchaMapper {

	public static CaptchaDtoResponse getCaptchaDtoResponse(String image, String secret) {

		CaptchaDtoResponse captcha = new CaptchaDtoResponse();
		captcha.setSecret(secret);
		captcha.setImage(image);

		return captcha;
	}
}
