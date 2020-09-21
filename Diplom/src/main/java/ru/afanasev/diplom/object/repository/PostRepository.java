package ru.afanasev.diplom.object.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.Tag;
import ru.afanasev.diplom.object.User;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

	@Query("select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1")
	List<Post> findModerationAndActivePosts(Pageable page);

	@Query("select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 and p.id = ?1")
	Post findModerationAndActivePostsbyId(Integer id);

	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 order by p.time desc")
	List<Post> findModerationAndActivePostsSortbyRecent(Pageable page);

	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 order by size(p.listComments) desc")
	List<Post> findModerationAndActivePostsSortbyPopular(Pageable page);

	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 order by size(p.listVotes) desc")
	List<Post> findModerationAndActivePostsSortbyBest(Pageable page);

	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 order by p.time")
	List<Post> findModerationAndActivePostsSortbyEarly(Pageable page);

	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 and (p.title like %:query% or p.text like %:query%)")
	List<Post> findModerationAndActivePostsByQuery(Pageable page, @Param("query") String query);

	@Query(value = "select p from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1 and cast(cast(p.time date) text) = :query")
	List<Post> findModerationAndActivePostsByDate(Pageable page, @Param("query") String query);

	@Query(value = "select p from Post p join p.listTags t where t.name = :query")
	List<Post> findModerationAndActivePostsByTag(Pageable page, @Param("query") String query);

	@Query(value = "select cast(cast(p.time date) text) from Post p where extract(year from p.time)  = :year")
	List<String> findDateByYears(@Param("year") Integer year);

	@Query(value = "select count(p) from Post p where cast(cast(p.time date) text) = :date")
	Integer findCountPostsByDate(@Param("date") String date);
	
	@Query(value = "select  extract(year from p.time) from Post p where p.moderationStatus = 'ACCEPTED' and p.isActive = 1")
	Set<Integer> findAllYears();
	
	@Query(value = "select cast((select count(p) from Post p join p.listTags t where t.name = ?1) double)/cast(count(p1) double) from  Post p1")
	Double findWeightByTag(@Param("tag")String tag);
	
	@Modifying
	@Query(value = "update Post p set p.viewCount = p.viewCount + 1 where p.id = ?1")
	void increaseViewCount(Integer id);
	
	@Query(value = "select p.listComments from Post p where p.id = ?1 and p.moderationStatus = 'ACCEPTED' and p.isActive = 1")
	List<PostComment> findCommentsByPostId(Integer id);
	
	@Query(value = "select p.listTags from Post p where p.id = ?1")
	Set<Tag>findTagsByPostId(Integer id);
}
