package ru.afanasev.diplom.object.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.afanasev.diplom.object.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

	@Query(value = "select t.name from Tag t")
	List<String> findAllNames();

	@Query(value = "select t.name from Tag t where t.name like %:query%")
	List<String> findAllNamesByQuery(@Param("query") String query);
}
