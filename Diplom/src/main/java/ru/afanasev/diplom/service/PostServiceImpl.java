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
import org.springframework.stereotype.Service;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.DTO.ListPostDtoResponse;
import ru.afanasev.diplom.object.DTO.UserNoPhotoDto;
import ru.afanasev.diplom.object.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Map<String, Object> getPosts(Integer offset, Integer limit, String mode) {

		Map<String, Object> posts = new HashMap<>();
		posts.put("count", getListPosts(offset, limit, mode).size());
		posts.put("posts", getListPosts(offset, limit, mode));

		return posts;

	}

	private List<ListPostDtoResponse> getListPosts(Integer offset, Integer limit, String mode) {

		List<ListPostDtoResponse> listPosts = new ArrayList<>();
		for (int i = offset; i < offset+limit; i++) {
			listPosts.add(createPostDto(postRepository.findModerationAndActivePostsbyId(i)));
		}

		switch (mode) {
		case "recent": 
			listPosts.sort((o1,o2)->o1.getTimestap().compareTo(o2.getTimestap()));
			Collections.reverse(listPosts);
			break;
		case "popular":
			listPosts.sort((o1,o2)->o1.getCommentCount().compareTo(o2.getCommentCount()));
			Collections.reverse(listPosts);
			break;
		case "best":
			listPosts.sort((o1,o2)->o1.getLikeCount().compareTo(o2.getLikeCount()));
			Collections.reverse(listPosts);
			break;
		case "early":
			listPosts.sort((o1,o2)->o1.getTimestap().compareTo(o2.getTimestap()));
			break;
		}
		
		return listPosts;
	}

	public static ListPostDtoResponse createPostDto(Post post) {
		
		ListPostDtoResponse postDto = new ListPostDtoResponse();
		postDto.setId(post.getId());
		postDto.setTimestap(Timestamp.valueOf(post.getTime()).getTime());
		UserNoPhotoDto user = new UserNoPhotoDto();
		user.setIdInteger(post.getUser().getId());
		user.setName(post.getUser().getName());
		postDto.setUser(user);
		postDto.setTitle(post.getTitle());
		postDto.setAnnounce(getAnnounce(post.getText()));
		Integer likeCount = (int) post.getListVotes().stream().filter(v -> v.getValue() > 0).count();
		Integer dislikeCount = (int) post.getListVotes().stream().filter(v -> v.getValue() < 0).count();
		postDto.setLikeCount(likeCount);
		postDto.setDislikeCount(dislikeCount);
		postDto.setViewCount(post.getViewCount());

		return postDto;
	}
	
	public static String getAnnounce(String text) {
		
		String textWithoutTag = text.replaceAll("\\<[^>]*>", "");
		String announce;
		if (textWithoutTag.length()<200) {
			announce = textWithoutTag.substring(0, textWithoutTag.length());
		}
		announce = textWithoutTag.substring(0, 200);
		
		return announce;
	}

}
