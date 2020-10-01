package ru.afanasev.diplom.object.dto.tagDtos;

import java.util.List;
import java.util.Set;

import lombok.Data;


public class TagDtoResponse {
	
	private Set<TagDto> tags;

	public Set<TagDto> getTags() {
		return tags;
	}

	public void setTags(Set<TagDto> tags) {
		this.tags = tags;
	}
	
	
}
