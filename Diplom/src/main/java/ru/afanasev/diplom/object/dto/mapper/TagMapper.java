package ru.afanasev.diplom.object.dto.mapper;

import java.util.Set;

import antlr.collections.List;
import ru.afanasev.diplom.object.dto.tagDtos.TagDto;
import ru.afanasev.diplom.object.dto.tagDtos.TagDtoResponse;

public class TagMapper {
		
	public static TagDto entityToTagDto(String name, Double weight) {
		TagDto tagDto = new TagDto();
		tagDto.setName(name);
		tagDto.setWeight(weight);
		return tagDto;
	}
	public static TagDtoResponse entityToTagDtoResponse(Set<TagDto> tags) {
		TagDtoResponse tagDtoResponse = new TagDtoResponse();
		tagDtoResponse.setTags(tags);
		return tagDtoResponse;
	}
	
}
