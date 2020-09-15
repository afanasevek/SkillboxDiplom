package ru.afanasev.diplom.object.DTO;

import java.util.List;

import lombok.Data;
@Data
public class ApiPostAltDtoResponse {
	private Integer count;
	private List<ApiPostAltDto> listPosts;
}
