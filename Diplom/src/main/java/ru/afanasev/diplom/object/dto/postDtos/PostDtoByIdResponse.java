package ru.afanasev.diplom.object.dto.postDtos;

import java.util.List;

import ru.afanasev.diplom.object.dto.commentDtos.CommentDto;
import ru.afanasev.diplom.object.dto.userDtos.UserNoPhotoDto;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public UserNoPhotoDto getUser() {
		return user;
	}

	public void setUser(UserNoPhotoDto user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(Integer dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
