package ru.afanasev.diplom.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.afanasev.diplom.object.dto.postDtos.PostAltDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoResponse;
import ru.afanasev.diplom.service.PostService;
import ru.afanasev.diplom.service.PostServiceImpl;

@Controller
@RequestMapping("/api/post")
public class ApiPostController {

	@Autowired
	private PostService postservice;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PostDtoResponse getAllPosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String mode) {

		return postservice.getPosts(offset, limit, mode);
	}

	@GetMapping(value = "/search/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PostDtoResponse getSearchPosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String query) {

		return postservice.getPostsByQuery(offset, limit, query);
	}

	@GetMapping(value = "/byDate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PostAltDtoResponse getByDatePosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String date) {

		return postservice.getPostsByDate(offset, limit, date);
	}

	@GetMapping(value = "/byTag", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PostAltDtoResponse getByTagPosts(@RequestParam Integer offset, @RequestParam Integer limit, @RequestParam String tag) {
		return postservice.getPostsByTag(offset, limit, tag);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PostAltDtoResponse getById(@PathVariable Integer id) {
		return null;
	}
}

