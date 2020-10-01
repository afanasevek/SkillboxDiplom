package ru.afanasev.diplom.object.dto.postDtos;

import java.util.List;

import lombok.Data;

public class PostAltDtoResponse {
	private Integer count;
	private List<PostAltDto> listPosts;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<PostAltDto> getListPosts() {
		return listPosts;
	}
	public void setListPosts(List<PostAltDto> listPosts) {
		this.listPosts = listPosts;
	}
	
	
}
