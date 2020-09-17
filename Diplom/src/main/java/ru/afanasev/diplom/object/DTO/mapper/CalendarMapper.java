package ru.afanasev.diplom.object.DTO.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.afanasev.diplom.object.DTO.CalendarDtoResponse;

public class CalendarMapper {

	
	public static CalendarDtoResponse getCalendarDtoResponse(Set<Integer> years, Map<String, Integer> countPostsYears) {
		CalendarDtoResponse calendarDtoResponse = new CalendarDtoResponse();
		calendarDtoResponse.setYears(years);
		calendarDtoResponse.setPosts(countPostsYears);
		return calendarDtoResponse;
	}
}
