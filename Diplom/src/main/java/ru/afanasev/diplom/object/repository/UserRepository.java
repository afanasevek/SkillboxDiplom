package ru.afanasev.diplom.object.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.afanasev.diplom.object.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select u from User u where u.email = ?1")
	Optional<User> findByEmail(String email);

}
