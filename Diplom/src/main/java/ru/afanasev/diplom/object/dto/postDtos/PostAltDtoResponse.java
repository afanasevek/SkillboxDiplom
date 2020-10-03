package ru.afanasev.diplom.object.dto.postDtos;

import java.util.List;

public class PostAltDtoResponse {
	private Integer count;
	private List<PostAltDto> posts;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<PostAltDto> getPosts() {
		return posts;
	}
	public void setPosts(List<PostAltDto> posts) {
		this.posts = posts;
	}
	
	
	
}


