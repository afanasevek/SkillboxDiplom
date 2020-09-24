package ru.afanasev.diplom.service;

import java.util.Map;
import java.util.Set;

import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.generalDtos.CalendarDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoByIdResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoResponse;

public interface PostService {
	PostDtoResponse getPosts(Integer offset, Integer limit, String mode);

	PostDtoResponse getPostsByQuery(Integer offset, Integer limit, String query);

	PostAltDtoResponse getPostsByDate(Integer offset, Integer limit, String query);

	PostAltDtoResponse getPostsByTag(Integer offset, Integer limit, String query);

	Map<String, Integer> getCalendar(Integer[] year);
	
	 PostDtoByIdResponse getPostById(User user, Integer id);
	 
	 Set<Integer> getYears();

}
