package ru.afanasev.diplom.object.DTO;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class ApiPostDtoResponse {
		Integer count;
		List<ApiPostDto> listPosts;
}
