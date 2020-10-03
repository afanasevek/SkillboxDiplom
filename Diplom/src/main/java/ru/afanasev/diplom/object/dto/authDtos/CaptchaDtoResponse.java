package ru.afanasev.diplom.object.dto.authDtos;

public class CaptchaDtoResponse {

	String secret;
	String image;

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
