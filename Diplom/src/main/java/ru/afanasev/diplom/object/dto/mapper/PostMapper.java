package ru.afanasev.diplom.object.dto.mapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

import ru.afanasev.diplom.object.ModerationStatus;
import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.Tag;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.commentDtos.CommentDto;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDto;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDto;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoByIdResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.SendPostDtoRequest;
import ru.afanasev.diplom.object.dto.userDtos.UserNoPhotoDto;
import ru.afanasev.diplom.service.PostServiceImpl;
import ru.afanasev.diplom.service.Utils;

public class PostMapper {

	public static PostDto entityToApiPostDto(UserNoPhotoDto user, Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTimestap(Timestamp.valueOf(post.getTime()).getTime());
		postDto.setUser(user);
		postDto.setTitle(post.getTitle());
		postDto.setAnnounce(PostServiceImpl.getAnnounce(Utils.removeHtmlTags(post.getText())));
		Integer likeCount = (int) post.getListVotes().stream().filter(v -> v.getValue() > 0).count();
		Integer dislikeCount = (int) post.getListVotes().stream().filter(v -> v.getValue() < 0).count();
		postDto.setLikeCount(likeCount);
		postDto.setDislikeCount(dislikeCount);
		postDto.setCommentCount(post.getListComments().size());
		postDto.setViewCount(post.getViewCount());

		return postDto;
	}

	public static PostDtoResponse entityToApiPostDtoResponse(Integer count, List<PostDto> posts) {
		PostDtoResponse response = new PostDtoResponse();
		response.setCount(count);
		response.setPosts(posts);

		return response;
	}

	public static PostAltDto entityToApiPostAltDto(UserNoPhotoDto user, Post post) {
		PostAltDto postDto = new PostAltDto();
		postDto.setId(post.getId());
		postDto.setTimestap(Timestamp.valueOf(post.getTime()).getTime());
		postDto.setUser(user);
		postDto.setTitle(post.getTitle());
		postDto.setAnnounce(PostServiceImpl.getAnnounce(Utils.removeHtmlTags(post.getText())));
		Integer likeCount = (int) post.getListVotes().stream().filter(v -> v.getValue() > 0).count();
		Integer dislikeCount = (int) post.getListVotes().stream().filter(v -> v.getValue() < 0).count();
		postDto.setLikeCount(likeCount);
		postDto.setDislikeCount(dislikeCount);
		postDto.setCommentCount(post.getListComments().size());
		postDto.setViewCount(post.getViewCount());

		return postDto;
	}

	public static PostAltDtoResponse entityToApiPostAltDtoResponse(Integer count, List<PostAltDto> listPosts) {
		PostAltDtoResponse response = new PostAltDtoResponse();
		response.setCount(count);
		response.setPosts(listPosts);

		return response;
	}

	public static PostDtoByIdResponse entityToApiPostDtoByIdResponse(Post post, UserNoPhotoDto user,
			List<CommentDto> comments, List<String> tags) {
		PostDtoByIdResponse postDtoByIdResponse = new PostDtoByIdResponse();
		postDtoByIdResponse.setId(post.getId());
		postDtoByIdResponse.setTimestamp(Timestamp.valueOf(post.getTime()).getTime());
		switch (post.getIsActive()) {
		case 1:
			postDtoByIdResponse.setActive(true);
			break;
		case -1:
			postDtoByIdResponse.setActive(false);
			break;
		}

		postDtoByIdResponse.setUser(user);
		postDtoByIdResponse.setTitle(post.getTitle());
		postDtoByIdResponse.setText(post.getText());
		postDtoByIdResponse.setLikeCount((int) post.getListVotes().stream().filter(x -> x.getValue() > 0).count());
		postDtoByIdResponse.setDislikeCount((int) post.getListVotes().stream().filter(x -> x.getValue() < 0).count());
		postDtoByIdResponse.setViewCount(post.getViewCount());
		postDtoByIdResponse.setComments(comments);
		postDtoByIdResponse.setTags(tags);

		return postDtoByIdResponse;
	}

	public static Post reqToPost(SendPostDtoRequest request, User user) {
		Post post = new Post();
		LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(request.getTimestamp()),
				TimeZone.getDefault().toZoneId());
		if (time.isBefore(LocalDateTime.now())) {
			time = LocalDateTime.now();
		}
		post.setTime(time);
		post.setIsActive(request.getActive());
		post.setUser(user);
		post.setTitle(request.getTitle());
		post.setText(request.getText());
		post.setViewCount(0);
		post.setModerationStatus(ModerationStatus.NEW);
		for (String nameTag : request.getTags()) {
			Tag tag = new Tag();
			tag.setName(nameTag);
			post.addTag(tag);
		}

		return post;

	}
}
