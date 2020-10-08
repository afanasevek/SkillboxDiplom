package ru.afanasev.diplom.service;

import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.commentDtos.CommentDtoRequest;

public interface CommentService {
	Integer sendCommentToPost(User user, CommentDtoRequest request);
}
