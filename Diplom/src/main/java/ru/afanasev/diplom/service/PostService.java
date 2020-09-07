package ru.afanasev.diplom.service;

import java.util.Map;

public interface PostService {
	Map<String, Object>  getPosts(Integer offset, Integer limit, String mode);
}
