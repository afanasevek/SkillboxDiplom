package ru.afanasev.diplom.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoResponse;
import ru.afanasev.diplom.service.PostService;
import ru.afanasev.diplom.service.PostServiceImpl;

@RestController
@RequestMapping("/api/post")
public class ApiPostController {

	@Autowired
	private PostService postservice;

	@PreAuthorize("permitAll()")
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostDtoResponse getAllPosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String mode) {

		return postservice.getPosts(offset, limit, mode);
	}

	@PreAuthorize("permitAll()")
	@GetMapping(value = "/search/", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostDtoResponse getSearchPosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String query) {

		return postservice.getPostsByQuery(offset, limit, query);
	}

	@PreAuthorize("permitAll()")
	@GetMapping(value = "/byDate", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostAltDtoResponse getByDatePosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String date) {

		return postservice.getPostsByDate(offset, limit, date);
	}

	@PreAuthorize("permitAll()")
	@GetMapping(value = "/byTag", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostAltDtoResponse getByTagPosts(@RequestParam Integer offset, @RequestParam Integer limit, @RequestParam String tag) {
		return postservice.getPostsByTag(offset, limit, tag);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostAltDtoResponse getById(@RequestParam(required = false) User user, @PathVariable Integer id) {
		return null;
	}
}

