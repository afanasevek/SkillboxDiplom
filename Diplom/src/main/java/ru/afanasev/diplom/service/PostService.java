package ru.afanasev.diplom.service;

import java.util.Map;

import ru.afanasev.diplom.object.DTO.ApiPostAltDtoResponse;
import ru.afanasev.diplom.object.DTO.ApiPostDtoResponse;
import ru.afanasev.diplom.object.DTO.CalendarDtoResponse;

public interface PostService {
	ApiPostDtoResponse getPosts(Integer offset, Integer limit, String mode);

	ApiPostDtoResponse getPostsByQuery(Integer offset, Integer limit, String query);

	ApiPostAltDtoResponse getPostsByDate(Integer offset, Integer limit, String query);

	ApiPostAltDtoResponse getPostsByTag(Integer offset, Integer limit, String query);

	CalendarDtoResponse getCalendar(Integer[] year);

}
