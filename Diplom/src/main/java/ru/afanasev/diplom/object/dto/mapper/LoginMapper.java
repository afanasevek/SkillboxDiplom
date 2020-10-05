package ru.afanasev.diplom.object.dto.mapper;

import ru.afanasev.diplom.object.dto.authDtos.LoginDtoResponse;
import ru.afanasev.diplom.object.dto.authDtos.LoginErrorDtoResponse;
import ru.afanasev.diplom.object.dto.authDtos.LoginSuccessDtoResponse;
import ru.afanasev.diplom.object.dto.userDtos.UserLoginDtoResponse;

public class LoginMapper {
	public static LoginDtoResponse getGoodLoginDtoRespose(UserLoginDtoResponse user) {
	
			LoginSuccessDtoResponse loginSuccessDtoResponse = new LoginSuccessDtoResponse();
			loginSuccessDtoResponse.setResult(true);
			loginSuccessDtoResponse.setUser(user);
			return loginSuccessDtoResponse;
		
	}
	
	public static LoginDtoResponse getLoginError() {
		
		LoginErrorDtoResponse loginErrorDtoResponse = new LoginErrorDtoResponse();
		loginErrorDtoResponse.setResult(false);
		return loginErrorDtoResponse;

	}
}
