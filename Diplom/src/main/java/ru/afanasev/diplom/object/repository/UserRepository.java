package ru.afanasev.diplom.object.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.afanasev.diplom.object.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
