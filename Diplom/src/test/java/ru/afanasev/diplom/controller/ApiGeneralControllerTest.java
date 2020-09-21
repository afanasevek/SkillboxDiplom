package ru.afanasev.diplom.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.afanasev.diplom.config.ConfigProperties;
import ru.afanasev.diplom.object.ModerationStatus;
import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.PostVote;
import ru.afanasev.diplom.object.Tag;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.generalDtos.CalendarDtoResponse;
import ru.afanasev.diplom.object.dto.generalDtos.InitDtoResponse;
import ru.afanasev.diplom.object.dto.tagDtos.TagDto;
import ru.afanasev.diplom.object.dto.tagDtos.TagDtoResponse;
import ru.afanasev.diplom.object.repository.PostCommentRepository;
import ru.afanasev.diplom.object.repository.PostRepository;
import ru.afanasev.diplom.object.repository.PostVoteRepository;
import ru.afanasev.diplom.object.repository.TagRepository;
import ru.afanasev.diplom.object.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ApiGeneralControllerTest {

	@Autowired
	private ConfigProperties configProperties;
	@Autowired
	private MockMvc mocMvc;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostVoteRepository postVoteRepository;
	@Autowired
	private PostCommentRepository postCommentRepository;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private TagRepository tagRepository;

	@BeforeEach
	public void setUp() {

		User user = new User();
		user.setId(1);
		user.setName("test_user");
		user.setEmail("test@test.com");
		user.setPassword("test");
		user.setIsModerator((byte) 1);
		user.setRegTime(LocalDateTime.now().minusMonths(4).minusDays(12));
		userRepository.save(user);

		Post post = new Post();
		post.setId(1);
		post.setIsActive((byte) 1);
		post.setModerationStatus(ModerationStatus.ACCEPTED);
		post.setUser(user);
		post.setModerator(user);
		post.setTitle("testing...");
		post.setText("testing so much");
		post.setViewCount(150);
		post.setTime(LocalDateTime.of(2005, 1, 1, 0, 0, 0));
		postRepository.save(post);

		PostVote postVote = new PostVote();
		postVote.setId(1);
		postVote.setPost(post);
		postVote.setUser(user);
		postVote.setValue((byte) 1);
		postVote.setTime(LocalDateTime.now().minusMonths(1).minusDays(10));
		postVoteRepository.save(postVote);

		PostComment comment = new PostComment();
		comment.setId(1);
		comment.setPost(post);
		comment.setText("testing comment");
		comment.setTime(LocalDateTime.now());
		comment.setUser(user);
		postCommentRepository.save(comment);

		User user1 = new User();
		user1.setId(2);
		user1.setName("test_user2");
		user1.setEmail("test2@test.com");
		user1.setPassword("test2");
		user1.setIsModerator((byte) 1);
		user1.setRegTime(LocalDateTime.now().minusMonths(10).minusDays(2));
		userRepository.save(user1);

		Post post1 = new Post();
		post1.setId(2);
		post1.setIsActive((byte) 1);
		post1.setModerationStatus(ModerationStatus.ACCEPTED);
		post1.setUser(user1);
		post1.setModerator(user1);
		post1.setTitle("testing 2..");
		post1.setText("testing so much2");
		post1.setViewCount(15000);
		post1.setTime(LocalDateTime.of(2020, 1, 2, 0, 0, 0));
		Tag tag = new Tag();
		tag.setId(1);
		tag.setName("hope");
		tagRepository.save(tag);
		post1.addTag(tag);
		postRepository.save(post1);

		PostVote postVote1 = new PostVote();
		postVote1.setUser(user1);
		postVote1.setId(2);
		postVote1.setPost(post1);
		postVote1.setValue((byte) 1);
		postVote1.setTime(LocalDateTime.now().minusMonths(1).minusDays(10));
		postVoteRepository.save(postVote1);

		User user2 = new User();
		user2.setId(3);
		user2.setName("test3_user");
		user2.setEmail("test3@test.com");
		user2.setPassword("test3");
		user2.setIsModerator((byte) 1);
		user2.setRegTime(LocalDateTime.now().minusMonths(4).minusDays(12));
		userRepository.save(user2);

		Post post2 = new Post();
		post2.setId(3);
		post2.setIsActive((byte) -1);
		post2.setModerationStatus(ModerationStatus.ACCEPTED);
		post2.setUser(user2);
		post2.setModerator(user2);
		post2.setTitle("testing3...");
		post2.setText("testing so much3");
		post2.setViewCount(1510);
		post2.setTime(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
		postRepository.save(post2);

		PostVote postVote2 = new PostVote();
		postVote2.setPost(post1);
		postVote2.setId(3);
		postVote2.setUser(user2);
		postVote2.setValue((byte) 1);
		postVote2.setTime(LocalDateTime.now().minusMonths(1).minusDays(10));
		postVoteRepository.save(postVote2);

		Post post3 = new Post();
		post3.setId(4);
		post3.setIsActive((byte) 1);
		post3.setModerationStatus(ModerationStatus.ACCEPTED);
		post3.setUser(user2);
		post3.setModerator(user2);
		post3.setTitle("testing3...");
		post3.setText("testing so much3");
		post3.setViewCount(1);
		post3.setTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
		Tag tag2 = new Tag();
		tag2.setId(2);
		tag2.setName("test");
		tagRepository.save(tag2);
		post3.addTag(tag2);
		postRepository.save(post3);

	}

	@Test
	void testInit() throws Exception {
		InitDtoResponse init = new InitDtoResponse();
		init.setCopyright("Afanasev Evgeny");
		init.setCopyrightFrom("2020");
		init.setEmail("afanasevek@gmail.com");
		init.setSubtitle("Developer's Stories");
		init.setPhone("+7 999 516 80-45");
		init.setTitle("DevPub");
		mocMvc.perform(get("/api/init/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(init)));

	}

	@Test
	void testGetCalendarQueryNull() throws Exception {
		CalendarDtoResponse calendarDtoResponse = new CalendarDtoResponse();
		Set<Integer> years = new TreeSet<>();
		years.add(2020);
		years.add(2005);
		years.add(2020);
		calendarDtoResponse.setYears(years);
		Map<String, Integer> posts = new TreeMap<>();
		posts.put("2020-01-01", 1);
		posts.put("2020-01-02", 1);
		calendarDtoResponse.setPosts(posts);
		mocMvc.perform(get("/api/calendar/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(calendarDtoResponse)));
	}

	@Test
	void testGetCalendarQuery() throws Exception {
		CalendarDtoResponse calendarDtoResponse = new CalendarDtoResponse();
		Set<Integer> years = new TreeSet<>();
		years.add(2020);
		years.add(2005);
		years.add(2020);
		calendarDtoResponse.setYears(years);
		Map<String, Integer> posts = new TreeMap<>();
		posts.put("2005-01-01", 1);
		calendarDtoResponse.setPosts(posts);
		mocMvc.perform(get("/api/calendar/").param("year", "2005")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(calendarDtoResponse)));

	}

	@Test
	void testGetTag() throws Exception {

		TagDtoResponse tagDtoResponse = new TagDtoResponse();
		Set<TagDto> tags = new LinkedHashSet<>();
		TagDto tagDto = new TagDto();
		tagDto.setName("hope");
		tagDto.setWeight(1.0);
		tags.add(tagDto);
		TagDto tagDto2 = new TagDto();
		tagDto2.setName("test");
		tagDto2.setWeight(1.0);
		tags.add(tagDto);
		tags.add(tagDto2);
		tagDtoResponse.setTags(tags);

		mocMvc.perform(get("/api/tag/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(tagDtoResponse)));

	}

}
