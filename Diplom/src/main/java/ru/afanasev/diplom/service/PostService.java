package ru.afanasev.diplom.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.generalDtos.CalendarDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDto;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoByIdResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoResponse;

public interface PostService {
	PostDtoResponse getPosts(Integer offset, Integer limit, String mode);

	PostDtoResponse getPostsByQuery(Integer offset, Integer limit, String query);

	PostAltDtoResponse getPostsByDate(Integer offset, Integer limit, String query);

	PostAltDtoResponse getPostsByTag(Integer offset, Integer limit, String query);

	Map<String, Integer> getCalendar(Integer[] year);
	
	 Post getPostById(User user, Integer id);
	 
	 Set<Integer> getYears();
	 
	 List<PostComment> getListCommentsById(Integer id);
	 
	 List<String> getTagByPostId(Integer id);
	 
	 List<PostAltDto> getListModerationPostByModerator(User moderator, Integer offset, Integer limit,String status);

}
