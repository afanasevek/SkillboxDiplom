package ru.afanasev.diplom.object.dto.commentDtos;

import ru.afanasev.diplom.object.dto.userDtos.UserWithPhotoDto;

public class CommentDto {

	private Integer id;
	private Long timestamp;
	private String text;
	private UserWithPhotoDto user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserWithPhotoDto getUser() {
		return user;
	}

	public void setUser(UserWithPhotoDto user) {
		this.user = user;
	}

}
