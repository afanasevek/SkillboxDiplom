package ru.afanasev.diplom.object.dto.postDtos;

import java.util.List;

public class PostDtoResponse {
	private Integer count;
	private List<PostDto> posts;

	public Integer getCount() {
		return count;
	}

	public List<PostDto> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDto> posts) {
		this.posts = posts;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
