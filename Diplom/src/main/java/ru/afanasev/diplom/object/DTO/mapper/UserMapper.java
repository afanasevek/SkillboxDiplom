package ru.afanasev.diplom.object.DTO.mapper;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.DTO.UserNoPhotoDto;

public class UserMapper {

	
	public static UserNoPhotoDto entitytoUserNoPhotoDto(Post post) {
		
		UserNoPhotoDto userNoPhotoDto = new UserNoPhotoDto();
		userNoPhotoDto.setIdInteger(post.getUser().getId());
		userNoPhotoDto.setName(post.getUser().getName());
		
		return userNoPhotoDto;
	}
	
	
	
}
