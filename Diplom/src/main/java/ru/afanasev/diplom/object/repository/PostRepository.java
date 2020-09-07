package ru.afanasev.diplom.object.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.afanasev.diplom.object.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	@Query("select p from Post p where p.moderationStatus = ACCEPTED and p.isActive = 1")
	List<Post> findModerationAndActivePosts();
	
	@Query("select p from Post p where p.moderationStatus = ACCEPTED and p.isActive = 1 and p.id = ?1")
	Post findModerationAndActivePostsbyId(Integer id);
	
}
