package ru.afanasev.diplom.object.dto.postDtos;

import java.util.List;

import lombok.Data;
@Data
public class PostAltDtoResponse {
	private Integer count;
	private List<PostAltDto> listPosts;
}
