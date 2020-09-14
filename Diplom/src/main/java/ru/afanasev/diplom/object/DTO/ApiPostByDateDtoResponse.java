package ru.afanasev.diplom.object.DTO;

import java.util.List;

import lombok.Data;
@Data
public class ApiPostByDateDtoResponse {
	private Integer count;
	private List<ApiPostByDateDto> listPosts;
}
