package ru.afanasev.diplom.object.dto.authDtos;

public class LoginSuccessDtoResponse extends LoginDtoResponse{
	
	private UserLoginDtoResponse user;

	public UserLoginDtoResponse getUser() {
		return user;
	}

	public void setUser(UserLoginDtoResponse user) {
		this.user = user;
	}

}
