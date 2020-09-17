package ru.afanasev.diplom.object.DTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class CalendarDtoResponse {
	
	private Set<Integer> years;
	private Map<String, Integer> posts;
	

}
