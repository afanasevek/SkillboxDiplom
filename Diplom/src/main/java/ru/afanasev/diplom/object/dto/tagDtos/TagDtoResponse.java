package ru.afanasev.diplom.object.dto.tagDtos;

import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class TagDtoResponse {
	
	private Set<TagDto> tags;
}
