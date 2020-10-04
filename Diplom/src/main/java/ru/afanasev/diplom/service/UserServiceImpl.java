package ru.afanasev.diplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username).get();

		return user;
	}

}
