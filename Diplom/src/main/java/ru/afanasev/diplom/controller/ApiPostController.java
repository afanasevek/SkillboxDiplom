package ru.afanasev.diplom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.mapper.CommentMapper;
import ru.afanasev.diplom.object.dto.mapper.PostMapper;
import ru.afanasev.diplom.object.dto.mapper.UserMapper;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDto;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoByIdResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.SendPostDtoRequest;
import ru.afanasev.diplom.object.dto.postDtos.SendPostDtoResponse;
import ru.afanasev.diplom.service.PostService;
import ru.afanasev.diplom.service.PostServiceImpl;

@RestController
@RequestMapping("/api")
public class ApiPostController {

	private final PostService postservice;

	public ApiPostController(PostService postservice) {
		super();
		this.postservice = postservice;
	}

	@GetMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostDtoResponse getAllPosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String mode) {

		return postservice.getPosts(offset, limit, mode);
	}

	@GetMapping(value = "/post/search/", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostDtoResponse getSearchPosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String query) {

		return postservice.getPostsByQuery(offset, limit, query);
	}

	@GetMapping(value = "/post/byDate", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostAltDtoResponse getByDatePosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String date) {

		return postservice.getPostsByDate(offset, limit, date);
	}

	@GetMapping(value = "/post/byTag", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostAltDtoResponse getByTagPosts(@RequestParam Integer offset, @RequestParam Integer limit,
			@RequestParam String tag) {

		List<PostAltDto> listPostsByTag = postservice.getPostsByTag(offset, limit, tag);

		return PostMapper.entityToApiPostAltDtoResponse(listPostsByTag.size(), listPostsByTag);
	}

	@GetMapping(value = "/post/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostDtoByIdResponse getById(@AuthenticationPrincipal User user, @PathVariable Integer id) {

		Post post = postservice.getPostById(user, id);
		List<PostComment> listComments = postservice.getListCommentsById(id);

		return PostMapper.entityToApiPostDtoByIdResponse(post, UserMapper.entitytoUserNoPhotoDto(post),
				CommentMapper.entityToCommentDto(listComments), postservice.getTagByPostId(id));
	}

	@GetMapping(value = "/post/moderation", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostAltDtoResponse getModerationPosts(@AuthenticationPrincipal User user, @RequestParam Integer offset,
			@RequestParam Integer limit, @RequestParam String status) {

		List<PostAltDto> listPosts = postservice.getListModerationPostByModerator(user, offset, limit, status);

		return PostMapper.entityToApiPostAltDtoResponse(listPosts.size(), listPosts);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public SendPostDtoResponse sendPost(SendPostDtoRequest post, @AuthenticationPrincipal User user) {
			
		return postservice.sendPost(post, user);
	}
}
