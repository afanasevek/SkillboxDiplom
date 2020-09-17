package ru.afanasev.diplom.object.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiPostAltDto {

	private Integer id;
	private Long timestap;
	private String title;
	private String announce;
	private Integer likeCount;
	private Integer dislikeCount;
	private Integer commentCount;
	private Integer viewCount;
	private UserNoPhotoDto user;
}
