package ru.afanasev.diplom.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.afanasev.diplom.exeption.CommentException;
import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.commentDtos.CommentDtoRequest;
import ru.afanasev.diplom.object.dto.mapper.CommentMapper;
import ru.afanasev.diplom.object.repository.PostCommentRepository;
import ru.afanasev.diplom.object.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	private final PostCommentRepository postCommentRepository;
	private final PostRepository postRepository;

	public CommentServiceImpl(PostCommentRepository postCommentRepository, PostRepository postRepository) {
		this.postCommentRepository = postCommentRepository;
		this.postRepository = postRepository;
	}

	@Override
	public Integer sendCommentToPost(User user, CommentDtoRequest request) {
		if (Utils.removeHtmlTags(request.getText()).length() < 10) {
			
		}
		if (Utils.removeHtmlTags(request.getText()).length() == 0) {
			
		}
		
		Post post = null;
		PostComment parentComment = null;
		Optional<PostComment> commentOptional = null;
		Optional<Post> postOptional = postRepository.findById(request.getPost_id());

		if (request.getParent_id() != null) {
			commentOptional = postCommentRepository.findById(request.getParent_id());
			if (commentOptional.isEmpty()) {
				throw new CommentException();
			}
			parentComment = commentOptional.get();
		}
		if (request.getPost_id() != null) {
			post = postOptional.get();
		} else {
			throw new CommentException();
		}

		PostComment comment = CommentMapper.requestToEntity(user, request.getText(), post, parentComment);
		PostComment commentSaved = postCommentRepository.save(comment);
		System.out.println(commentSaved.getId());
		return commentSaved.getId();
	}
}
