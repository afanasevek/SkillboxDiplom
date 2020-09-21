package ru.afanasev.diplom.object.dto.mapper;

import ru.afanasev.diplom.object.dto.tagDtos.TagDto;

public class TagMapper {
		
	public static TagDto entityToTagDto(String name, Double weight) {
		TagDto tagDto = new TagDto();
		tagDto.setName(name);
		tagDto.setWeight(weight);
		return tagDto;
	}
	
}
