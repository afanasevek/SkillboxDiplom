package ru.afanasev.diplom.object.dto.mapper;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.userDtos.UserNoPhotoDto;
import ru.afanasev.diplom.object.dto.userDtos.UserWithPhotoDto;

public class UserMapper {

	public static UserNoPhotoDto entitytoUserNoPhotoDto(Post post) {

		UserNoPhotoDto userNoPhotoDto = new UserNoPhotoDto();
		userNoPhotoDto.setId(post.getUser().getId());
		userNoPhotoDto.setName(post.getUser().getName());

		return userNoPhotoDto;
	}
	
	public static UserWithPhotoDto entitytoUserWithPhotoDto(PostComment comment) {
		
		UserWithPhotoDto user = new UserWithPhotoDto();
		user.setId(comment.getUser().getId());
		user.setName(comment.getUser().getName());
		user.setPhoto(comment.getUser().getPhoto());
		
		return user;
	}

}
