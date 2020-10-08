package ru.afanasev.diplom.object.dto.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.commentDtos.CommentDto;
import ru.afanasev.diplom.object.dto.commentDtos.CommentDtoRequest;
import ru.afanasev.diplom.object.dto.commentDtos.SendCommentSeccessDtoResponse;

public class CommentMapper {

	public static List<CommentDto> entityToCommentDto(List<PostComment> comments) {

		List<CommentDto> commentDtos = new ArrayList<>();
		for (PostComment comment : comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setTimestamp(Timestamp.valueOf(comment.getTime()).getTime());
			commentDto.setText(comment.getText());

			commentDto.setUser(UserMapper.entitytoUserWithPhotoDto(comment));
		}

		return commentDtos;

	}
	
	public static PostComment requestToEntity(User user, String text, Post post, PostComment postComment) {
		PostComment comment = new PostComment();
		comment.setPost(post);
		comment.setPostComment(postComment);
		comment.setUser(user);
		comment.setTime(LocalDateTime.now());
		comment.setText(text);
		
		return comment;
	}
	
	public static SendCommentSeccessDtoResponse enityIdToSendCommentSeccessDtoResponse (Integer id) {
		SendCommentSeccessDtoResponse commentId = new SendCommentSeccessDtoResponse();
		commentId.setId(id);
		return commentId;
	}

}
