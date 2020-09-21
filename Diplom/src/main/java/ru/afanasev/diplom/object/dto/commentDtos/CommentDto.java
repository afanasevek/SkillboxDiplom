package ru.afanasev.diplom.object.dto.commentDtos;

import lombok.Data;
import ru.afanasev.diplom.object.dto.userDtos.UserWithPhotoDto;

@Data
public class CommentDto {
	
		private Integer id;
		private Long timestamp;
		private String text;
		private UserWithPhotoDto user;
}
