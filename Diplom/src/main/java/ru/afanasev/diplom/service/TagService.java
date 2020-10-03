package ru.afanasev.diplom.service;

import java.util.Set;

import ru.afanasev.diplom.object.dto.tagDtos.TagDto;

public interface TagService {
	Set<TagDto> getAllweight(String tag);
}
