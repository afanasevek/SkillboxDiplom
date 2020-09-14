package ru.afanasev.diplom.object.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.afanasev.diplom.object.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

}
