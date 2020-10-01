package ru.afanasev.diplom.object.dto.generalDtos;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;


public class CalendarDtoResponse {
	
	private Set<Integer> years;
	private Map<String, Integer> posts;
	public Set<Integer> getYears() {
		return years;
	}
	public void setYears(Set<Integer> years) {
		this.years = years;
	}
	public Map<String, Integer> getPosts() {
		return posts;
	}
	public void setPosts(Map<String, Integer> posts) {
		this.posts = posts;
	}
	
	

}
