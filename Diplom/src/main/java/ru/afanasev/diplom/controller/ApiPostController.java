package ru.afanasev.diplom.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.afanasev.diplom.object.DTO.ApiPostAltDtoResponse;
import ru.afanasev.diplom.object.DTO.ApiPostDtoResponse;
import ru.afanasev.diplom.service.PostService;
import ru.afanasev.diplom.service.PostServiceImpl;

@Controller
@RequestMapping("/api")
public class ApiPostController {

	@Autowired
	private PostService postservice;

	@GetMapping(value = "/post/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ApiPostDtoResponse getAllPosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String mode) {

		return postservice.getPosts(offset, limit, mode);
	}

	@GetMapping(value = "/post/search/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ApiPostDtoResponse getSearchPosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String query) {

		return postservice.getPostsByQuery(offset, limit, query);
	}

	@GetMapping(value = "/post/byDate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ApiPostAltDtoResponse getByDatePosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String date) {

		return postservice.getPostsByDate(offset, limit, date);
	}

	@GetMapping(value = "/post/byTag", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ApiPostAltDtoResponse getByTagPosts(@RequestParam Integer offset, @RequestParam Integer limit, @RequestParam String tag) {
		return postservice.getPostsByTag(offset, limit, tag);
	}
}
