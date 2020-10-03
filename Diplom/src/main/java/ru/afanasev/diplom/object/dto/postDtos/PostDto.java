package ru.afanasev.diplom.object.dto.postDtos;

import ru.afanasev.diplom.object.dto.userDtos.UserNoPhotoDto;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getTimestap() {
		return timestap;
	}

	public void setTimestap(Long timestap) {
		this.timestap = timestap;
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

	public String getAnnounce() {
		return announce;
	}

	public void setAnnounce(String announce) {
		this.announce = announce;
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

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

}
