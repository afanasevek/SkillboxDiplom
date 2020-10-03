package ru.afanasev.diplom.object.dto.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.dto.commentDtos.CommentDto;

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

}
