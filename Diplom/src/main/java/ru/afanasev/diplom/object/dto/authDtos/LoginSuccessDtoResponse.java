package ru.afanasev.diplom.object.dto.authDtos;

import ru.afanasev.diplom.object.dto.userDtos.UserLoginDtoResponse;

public class LoginSuccessDtoResponse extends LoginDtoResponse{
	
	private UserLoginDtoResponse user;

	public UserLoginDtoResponse getUser() {
		return user;
	}

	public void setUser(UserLoginDtoResponse user) {
		this.user = user;
	}

}
