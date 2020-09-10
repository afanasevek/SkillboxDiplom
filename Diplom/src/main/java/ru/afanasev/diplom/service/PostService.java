package ru.afanasev.diplom.service;

import java.util.Map;

import ru.afanasev.diplom.object.DTO.ApiPostDtoResponse;

public interface PostService {
	ApiPostDtoResponse  getPosts(Integer offset, Integer limit, String mode);
}
