package ru.afanasev.diplom.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.Tag;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.generalDtos.CalendarDtoResponse;
import ru.afanasev.diplom.object.dto.mapper.CalendarMapper;
import ru.afanasev.diplom.object.dto.mapper.CommentMapper;
import ru.afanasev.diplom.object.dto.mapper.PostMapper;
import ru.afanasev.diplom.object.dto.mapper.UserMapper;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDto;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDto;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoByIdResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoResponse;
import ru.afanasev.diplom.object.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDtoResponse getPosts(Integer offset, Integer limit, String mode) {

		return PostMapper.entityToApiPostDtoResponse(getListPosts(offset, limit, mode).size(),
				getListPosts(offset, limit, mode));
	}

	@Override
	public PostDtoResponse getPostsByQuery(Integer offset, Integer limit, String query) {

		return PostMapper.entityToApiPostDtoResponse(getListPostsByQuery(offset, limit, query).size(),
				getListPostsByQuery(offset, limit, query));
	}

	@Override
	public PostAltDtoResponse getPostsByDate(Integer offset, Integer limit, String query) {

		return PostMapper.entityToApiPostAltDtoResponse(getListPostsByDate(offset, limit, query).size(),
				getListPostsByDate(offset, limit, query));
	}

	@Override
	public PostAltDtoResponse getPostsByTag(Integer offset, Integer limit, String query) {

		return PostMapper.entityToApiPostAltDtoResponse(getListPostsByTag(offset, limit, query).size(),
				getListPostsByTag(offset, limit, query));
	}

	@Override
	public Map<String, Integer> getCalendar(Integer[] year) {
		if (year == null) {
			Integer[] currentYear = {Calendar.getInstance().get(Calendar.YEAR)};
			return getPostsCalendar(currentYear);
		}
		return getPostsCalendar(year);
	}
	
	@Override
	public PostDtoByIdResponse getPostById(User user, Integer id) {
		Post post = getPostById(id);;
		if (user.getIsModerator()!=1||post.getUser().equals(user)==false) {
			postRepository.increaseViewCount(id); 
		}
		
		return PostMapper.entityToApiPostDtoByIdResponse(
				post,
				UserMapper.entitytoUserNoPhotoDto(post),
				CommentMapper.entityToCommentDto(getListCommentsById(id)),
				getTagByPostId(id));
	}
	

	private List<PostDto> getListPosts(Integer offset, Integer limit, String mode) {

		List<PostDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset, limit + offset);
		List<Post> findPosts = new ArrayList<>();
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

	private List<PostDto> getListPostsByQuery(Integer offset, Integer limit, String query) {

		List<PostDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset, limit + offset);
		List<Post> findPosts = new ArrayList<>();
		if (query.isEmpty()) {
			findPosts = postRepository.findModerationAndActivePosts(page);
		} else {
			findPosts = postRepository.findModerationAndActivePostsByQuery(page, query);
			if (findPosts.isEmpty()) {
				return listPosts;
			}
		}
		addListPosts(findPosts, listPosts);

		return listPosts;
	}

	@Override
	public Set<Integer> getYears() {
		return postRepository.findAllYears();
	}

	private Map<String, Integer> getPostsCalendar(Integer[] year) {
		Map<String, Integer> countPostsYears = new TreeMap<>();
		for (Integer y : year) {
			for (String date : postRepository.findDateByYears(y)) {

				countPostsYears.put(date, postRepository.findCountPostsByDate(date));
			}
		}

		return countPostsYears;
	}

	private List<PostAltDto> getListPostsByDate(Integer offset, Integer limit, String query) {

		List<PostAltDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset, limit + offset);
		List<Post> findPosts = new ArrayList<>();
		findPosts = postRepository.findModerationAndActivePostsByDate(page, query);
		if (findPosts.isEmpty()) {
			return listPosts;
		}

		addListPostsDate(findPosts, listPosts);

		return listPosts;
	}

	private List<PostAltDto> getListPostsByTag(Integer offset, Integer limit, String query) {

		List<PostAltDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset, limit + offset);
		List<Post> findPosts = new ArrayList<>();
		findPosts = postRepository.findModerationAndActivePostsByTag(page, query);
		if (findPosts.isEmpty()) {
			return listPosts;
		}

		addListPostsDate(findPosts, listPosts);

		return listPosts;
	}

	public static String getAnnounce(String text) {

		String announce;
		if (text.length() < 200) {
			announce = text.substring(0, text.length());
			return announce;
		}
		announce = text.substring(0, 200);

		return announce;
	}

	private void addListPosts(List<Post> list, List<PostDto> listPostDto) {
		for (Post post : list) {
			listPostDto.add(PostMapper.entityToApiPostDto(UserMapper.entitytoUserNoPhotoDto(post), post));
		}
	}

	private void addListPostsDate(List<Post> list, List<PostAltDto> listPostDto) {
		for (Post post : list) {
			listPostDto.add(PostMapper.entityToApiPostAltDto(UserMapper.entitytoUserNoPhotoDto(post), post));
		}
	}
	
	private Post getPostById(Integer id) {
		return postRepository.findById(id).get();
	}
	
	private List <PostComment> getListCommentsById(Integer id){
		return postRepository.findCommentsByPostId(id);
	}
	private List<String> getTagByPostId(Integer id){
		List<String> tags = new ArrayList<>();
		for(Tag tag : postRepository.findTagsByPostId(id)) {
			tags.add(tag.getName());
		}
		return tags;
	}

}
