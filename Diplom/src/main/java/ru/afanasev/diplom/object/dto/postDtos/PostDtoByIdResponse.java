package ru.afanasev.diplom.object.dto.postDtos;

import java.util.List;

import lombok.Data;
import ru.afanasev.diplom.object.dto.commentDtos.CommentDto;
import ru.afanasev.diplom.object.dto.userDtos.UserNoPhotoDto;

@Data
public class PostDtoByIdResponse {
	
	private Integer id;
	private Long timestamp;
	private Boolean active;
	private UserNoPhotoDto user;
	private String title;
	private String text;
	private Integer likeCount;
	private Integer dislikeCount;
	private Integer viewCount;
	private List<CommentDto> comments;
	private List<String> tags;
}
