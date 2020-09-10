package ru.afanasev.diplom.object.DTO.mapper;

import java.sql.Timestamp;
import java.util.List;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.DTO.ApiPostDto;
import ru.afanasev.diplom.object.DTO.ApiPostDtoResponse;
import ru.afanasev.diplom.object.DTO.UserNoPhotoDto;
import ru.afanasev.diplom.service.PostServiceImpl;
import ru.afanasev.diplom.service.Utils;

public class PostMapper {
		
	public static ApiPostDto entityToApiPostDto(UserNoPhotoDto user, Post post) {
		ApiPostDto postDto = new ApiPostDto();
		postDto.setId(post.getId());
		postDto.setTimestap(Timestamp.valueOf(post.getTime()).getTime());
		postDto.setUser(user);
		postDto.setTitle(post.getTitle());
		postDto.setAnnounce(PostServiceImpl.getAnnounce(Utils.removeHtmlTags(post.getText())));
		Integer likeCount = (int) post.getListVotes().stream().filter(v -> v.getValue() > 0).count();
		Integer dislikeCount = (int) post.getListVotes().stream().filter(v -> v.getValue() < 0).count();
		postDto.setLikeCount(likeCount);
		postDto.setDislikeCount(dislikeCount);
		postDto.setViewCount(post.getViewCount());
		
		return postDto;
	}
	
	public static ApiPostDtoResponse entityToApiPostDtoResponse(Integer count, List<ApiPostDto> listPosts) {
		ApiPostDtoResponse response = new ApiPostDtoResponse();
		response.setCount(count);
		response.setListPosts(listPosts);
		
		return response;
	}
}
