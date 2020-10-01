package ru.afanasev.diplom.object.dto.mapper;

import ru.afanasev.diplom.object.dto.authDtos.CaptchaDtoResponse;

public class CaptchaMapper {
	
	public static CaptchaDtoResponse getCaptchaDtoResponse (String image, String secret) {
		StringBuilder builder = new StringBuilder();
		builder.append("data:image/png;base64, ");
		builder.append(image);
		CaptchaDtoResponse captcha = new CaptchaDtoResponse();
		captcha.setSecret(secret);
		captcha.setImage(builder.toString());
		
		return captcha;
	}
}
