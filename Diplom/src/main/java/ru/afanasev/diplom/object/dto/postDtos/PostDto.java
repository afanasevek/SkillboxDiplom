package ru.afanasev.diplom.object.dto.postDtos;

import lombok.Getter;
import lombok.Setter;
import ru.afanasev.diplom.object.dto.userDtos.UserNoPhotoDto;

@Getter
@Setter
public class PostDto {

	private Integer id;
	private Long timestap;
	private UserNoPhotoDto user;
	private String title;
	private String announce;
	private Integer likeCount;
	private Integer dislikeCount;
	private Integer commentCount;
	private Integer viewCount;

}
