package ru.afanasev.diplom.object.dto.postDtos;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

public class PostDtoResponse {
	private Integer count;
	private List<PostDto> listPosts;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<PostDto> getListPosts() {
		return listPosts;
	}
	public void setListPosts(List<PostDto> listPosts) {
		this.listPosts = listPosts;
	}
	
	
}
