package ru.afanasev.diplom.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.afanasev.diplom.object.DTO.ApiPostDtoResponse;
import ru.afanasev.diplom.service.PostService;
import ru.afanasev.diplom.service.PostServiceImpl;

@Controller
@RequestMapping("/api")
public class ApiPostController {
	
	@Autowired
	private PostService postservice;
	

	@GetMapping("/post/")
	ResponseEntity<ApiPostDtoResponse> getAllPosts(@RequestParam Integer offset, @RequestParam Integer limit, @RequestParam String mode){
		
		return ResponseEntity.ok(postservice.getPosts(offset, limit, mode));
	}
}
 