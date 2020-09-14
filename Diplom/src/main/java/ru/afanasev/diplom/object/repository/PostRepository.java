package ru.afanasev.diplom.object.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.afanasev.diplom.object.Post;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer>{
	
	@Query("select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1")
	List<Post> findModerationAndActivePosts(Pageable page);
	
	@Query("select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 and p.id = ?1")
	Post findModerationAndActivePostsbyId(Integer id);
	
	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 order by p.time desc")
	List<Post>findModerationAndActivePostsSortbyRecent(Pageable page); 
	
	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 order by size(p.listComments) desc")
	List<Post>findModerationAndActivePostsSortbyPopular(Pageable page); 
	
	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 order by size(p.listVotes) desc")
	List<Post>findModerationAndActivePostsSortbyBest(Pageable page); 
	
	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 order by p.time")
	List<Post>findModerationAndActivePostsSortbyEarly(Pageable page); 
	
	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 and (p.title like %:query% or p.text like %:query%)")
	List<Post>findModerationAndActivePostsByQuery(Pageable page, @Param("query")String query); 
	
	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 and cast(cast(p.time date) text) = :query")
	List<Post>findModerationAndActivePostsByDate(Pageable page, @Param("query")String query); 
}
