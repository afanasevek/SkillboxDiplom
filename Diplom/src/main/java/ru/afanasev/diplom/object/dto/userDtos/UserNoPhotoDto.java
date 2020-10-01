package ru.afanasev.diplom.object.dto.userDtos;

import lombok.Getter;

import lombok.Setter;
import lombok.ToString;


public class UserNoPhotoDto {
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
