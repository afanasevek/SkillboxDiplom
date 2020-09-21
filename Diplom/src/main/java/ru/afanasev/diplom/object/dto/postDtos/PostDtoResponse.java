package ru.afanasev.diplom.object.dto.postDtos;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class PostDtoResponse {
	private Integer count;
	private List<PostDto> listPosts;
}
