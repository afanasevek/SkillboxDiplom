package ru.afanasev.diplom.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.Case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.DTO.ApiPostDto;
import ru.afanasev.diplom.object.DTO.ApiPostDtoResponse;
import ru.afanasev.diplom.object.DTO.UserNoPhotoDto;
import ru.afanasev.diplom.object.DTO.mapper.PostMapper;
import ru.afanasev.diplom.object.DTO.mapper.UserMapper;
import ru.afanasev.diplom.object.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public ApiPostDtoResponse getPosts(Integer offset, Integer limit, String mode) {
		
		
		return PostMapper.entityToApiPostDtoResponse(getListPosts(offset, limit, mode).size(), getListPosts(offset, limit, mode));
	}
	
	@Override
	public ApiPostDtoResponse getPostsByQuery(Integer offset, Integer limit, String query) {

		return PostMapper.entityToApiPostDtoResponse(getListPostsByQuery(offset, limit, query).size(), getListPostsByQuery(offset, limit, query));
	}

	private List<ApiPostDto> getListPosts(Integer offset, Integer limit, String mode) {
		
		List<ApiPostDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset,limit+offset);
		List<Post> findPosts = new ArrayList();
		switch (mode) {
		case "recent": 

			findPosts = postRepository.findModerationAndActivePostsSortbyRecent(page);
			if (findPosts.isEmpty()) {
				return listPosts;
			}
			addListPosts(findPosts, listPosts);
			
			break;
		case "popular":
			findPosts = postRepository.findModerationAndActivePostsSortbyPopular(page);
			if (findPosts.isEmpty()) {
				return listPosts;
			}
			addListPosts(findPosts, listPosts);

			break;
		case "best":
			findPosts = postRepository.findModerationAndActivePostsSortbyBest(page);
			if (findPosts.isEmpty()) {
				return listPosts;
			}
			addListPosts(findPosts, listPosts);

			break;
		case "early":
			findPosts = postRepository.findModerationAndActivePostsSortbyEarly(page);
			if (findPosts.isEmpty()) {
				return listPosts;
			}
			addListPosts(findPosts, listPosts);

			break;
		}
		
		return listPosts;
	}
	
	private List<ApiPostDto> getListPostsByQuery(Integer offset, Integer limit, String query) {
		
		List<ApiPostDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset,limit+offset);
		List<Post> findPosts = new ArrayList();
		if(query.equals("")) {
			findPosts = postRepository.findModerationAndActivePosts(page);
		}else {
			findPosts = postRepository.findModerationAndActivePostsByQuery(page, query);
			if (findPosts.isEmpty()) {
				return listPosts;
			}
		}
		addListPosts(findPosts, listPosts);
		
		
		return listPosts;
	}

	
	public static String getAnnounce(String text) {
		
		String announce;
		if (text.length()<200) {
			announce = text.substring(0, text.length());
			return announce;
		}
		announce = text.substring(0, 200);
		
		return announce;
	}
	private void addListPosts(List<Post> list, List<ApiPostDto> listPostDto) {
		for (Post post : list) {
			listPostDto.add(PostMapper.entityToApiPostDto(UserMapper.entitytoUserNoPhotoDto(post), post));
		}
	}

}
