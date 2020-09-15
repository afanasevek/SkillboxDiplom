package ru.afanasev.diplom.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.afanasev.diplom.object.ModerationStatus;
import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.PostVote;
import ru.afanasev.diplom.object.Tag;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.DTO.ApiPostAltDto;
import ru.afanasev.diplom.object.DTO.ApiPostAltDtoResponse;
import ru.afanasev.diplom.object.DTO.ApiPostDto;
import ru.afanasev.diplom.object.DTO.ApiPostDtoResponse;
import ru.afanasev.diplom.object.DTO.UserNoPhotoDto;
import ru.afanasev.diplom.object.repository.PostCommentRepository;
import ru.afanasev.diplom.object.repository.PostRepository;
import ru.afanasev.diplom.object.repository.PostVoteRepository;
import ru.afanasev.diplom.object.repository.TagRepository;
import ru.afanasev.diplom.object.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ApiPostControllerTest {

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
		post1.setTime(LocalDateTime.of(1990, 1, 1, 0, 0, 0));
		Tag tag = new Tag();
		tag.setId(1);
		tag.setName("hope");
		tag.getListPosts().add(post1);
		tagRepository.save(tag);

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
		postRepository.save(post3);

	}

	@Test
	void testGetAllPostsRecent() throws Exception {

		ApiPostDto post01 = new ApiPostDto();
		post01.setId(2);
		UserNoPhotoDto user01 = new UserNoPhotoDto();
		user01.setId(2);
		user01.setName("test_user2");
		post01.setUser(user01);
		post01.setTimestap(Timestamp.valueOf(LocalDateTime.of(1990, 1, 1, 0, 0, 0)).getTime());
		post01.setTitle("testing 2..");
		post01.setAnnounce("testing so much2");
		post01.setLikeCount(2);
		post01.setDislikeCount(0);
		post01.setCommentCount(0);
		post01.setViewCount(15000);

		ApiPostDto post02 = new ApiPostDto();
		post02.setId(1);
		UserNoPhotoDto user02 = new UserNoPhotoDto();
		user02.setId(1);
		user02.setName("test_user");
		post02.setUser(user02);
		post02.setTimestap(Timestamp.valueOf(LocalDateTime.of(2005, 1, 1, 0, 0, 0)).getTime());
		post02.setTitle("testing...");
		post02.setAnnounce("testing so much");
		post02.setLikeCount(1);
		post02.setDislikeCount(0);
		post02.setCommentCount(1);
		post02.setViewCount(150);

		ApiPostDto post03 = new ApiPostDto();
		post03.setId(4);
		UserNoPhotoDto user03 = new UserNoPhotoDto();
		user03.setId(3);
		user03.setName("test3_user");
		post03.setUser(user03);
		post03.setTimestap(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0)).getTime());
		post03.setTitle("testing3...");
		post03.setAnnounce("testing so much3");
		post03.setLikeCount(0);
		post03.setDislikeCount(0);
		post03.setCommentCount(0);
		post03.setViewCount(1);
		List<ApiPostDto> testList = new ArrayList<>();
		testList.add(post03);
		testList.add(post02);
		testList.add(post01);
		ApiPostDtoResponse res = new ApiPostDtoResponse();
		res.setCount(3);
		res.setListPosts(testList);

		mocMvc.perform(get("/api/post/").param("offset", "0").param("limit", "10").param("mode", "recent"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(res)));
	}

	@Test
	void testGetAllPostsPopular() throws Exception {

		ApiPostDto post01 = new ApiPostDto();
		post01.setId(2);
		UserNoPhotoDto user01 = new UserNoPhotoDto();
		user01.setId(2);
		user01.setName("test_user2");
		post01.setUser(user01);
		post01.setTimestap(Timestamp.valueOf(LocalDateTime.of(1990, 1, 1, 0, 0, 0)).getTime());
		post01.setTitle("testing 2..");
		post01.setAnnounce("testing so much2");
		post01.setLikeCount(2);
		post01.setDislikeCount(0);
		post01.setCommentCount(0);
		post01.setViewCount(15000);

		ApiPostDto post02 = new ApiPostDto();
		post02.setId(1);
		UserNoPhotoDto user02 = new UserNoPhotoDto();
		user02.setId(1);
		user02.setName("test_user");
		post02.setUser(user02);
		post02.setTimestap(Timestamp.valueOf(LocalDateTime.of(2005, 1, 1, 0, 0, 0)).getTime());
		post02.setTitle("testing...");
		post02.setAnnounce("testing so much");
		post02.setLikeCount(1);
		post02.setDislikeCount(0);
		post02.setCommentCount(1);
		post02.setViewCount(150);

		ApiPostDto post03 = new ApiPostDto();
		post03.setId(4);
		UserNoPhotoDto user03 = new UserNoPhotoDto();
		user03.setId(3);
		user03.setName("test3_user");
		post03.setUser(user03);
		post03.setTimestap(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0)).getTime());
		post03.setTitle("testing3...");
		post03.setAnnounce("testing so much3");
		post03.setLikeCount(0);
		post03.setDislikeCount(0);
		post03.setCommentCount(0);
		post03.setViewCount(1);
		List<ApiPostDto> testList = new ArrayList<>();
		testList.add(post02);
		testList.add(post03);
		testList.add(post01);
		ApiPostDtoResponse res = new ApiPostDtoResponse();
		res.setCount(3);
		res.setListPosts(testList);

		mocMvc.perform(get("/api/post/").param("offset", "0").param("limit", "10").param("mode", "popular"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(res)));

	}

	@Test
	void testGetAllPostsBest() throws Exception {

		ApiPostDto post01 = new ApiPostDto();
		post01.setId(2);
		UserNoPhotoDto user01 = new UserNoPhotoDto();
		user01.setId(2);
		user01.setName("test_user2");
		post01.setUser(user01);
		post01.setTimestap(Timestamp.valueOf(LocalDateTime.of(1990, 1, 1, 0, 0, 0)).getTime());
		post01.setTitle("testing 2..");
		post01.setAnnounce("testing so much2");
		post01.setLikeCount(2);
		post01.setDislikeCount(0);
		post01.setCommentCount(0);
		post01.setViewCount(15000);

		ApiPostDto post02 = new ApiPostDto();
		post02.setId(1);
		UserNoPhotoDto user02 = new UserNoPhotoDto();
		user02.setId(1);
		user02.setName("test_user");
		post02.setUser(user02);
		post02.setTimestap(Timestamp.valueOf(LocalDateTime.of(2005, 1, 1, 0, 0, 0)).getTime());
		post02.setTitle("testing...");
		post02.setAnnounce("testing so much");
		post02.setLikeCount(1);
		post02.setDislikeCount(0);
		post02.setCommentCount(1);
		post02.setViewCount(150);

		ApiPostDto post03 = new ApiPostDto();
		post03.setId(4);
		UserNoPhotoDto user03 = new UserNoPhotoDto();
		user03.setId(3);
		user03.setName("test3_user");
		post03.setUser(user03);
		post03.setTimestap(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0)).getTime());
		post03.setTitle("testing3...");
		post03.setAnnounce("testing so much3");
		post03.setLikeCount(0);
		post03.setDislikeCount(0);
		post03.setCommentCount(0);
		post03.setViewCount(1);
		List<ApiPostDto> testList = new ArrayList<>();
		testList.add(post01);
		testList.add(post02);
		testList.add(post03);
		ApiPostDtoResponse res = new ApiPostDtoResponse();
		res.setCount(3);
		res.setListPosts(testList);

		mocMvc.perform(get("/api/post/").param("offset", "0").param("limit", "10").param("mode", "best")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(res)));

	}

	@Test
	void testGetAllPostsEarly() throws Exception {

		ApiPostDto post01 = new ApiPostDto();
		post01.setId(2);
		UserNoPhotoDto user01 = new UserNoPhotoDto();
		user01.setId(2);
		user01.setName("test_user2");
		post01.setUser(user01);
		post01.setTimestap(Timestamp.valueOf(LocalDateTime.of(1990, 1, 1, 0, 0, 0)).getTime());
		post01.setTitle("testing 2..");
		post01.setAnnounce("testing so much2");
		post01.setLikeCount(2);
		post01.setDislikeCount(0);
		post01.setCommentCount(0);
		post01.setViewCount(15000);

		ApiPostDto post02 = new ApiPostDto();
		post02.setId(1);
		UserNoPhotoDto user02 = new UserNoPhotoDto();
		user02.setId(1);
		user02.setName("test_user");
		post02.setUser(user02);
		post02.setTimestap(Timestamp.valueOf(LocalDateTime.of(2005, 1, 1, 0, 0, 0)).getTime());
		post02.setTitle("testing...");
		post02.setAnnounce("testing so much");
		post02.setLikeCount(1);
		post02.setDislikeCount(0);
		post02.setCommentCount(1);
		post02.setViewCount(150);

		ApiPostDto post03 = new ApiPostDto();
		post03.setId(4);
		UserNoPhotoDto user03 = new UserNoPhotoDto();
		user03.setId(3);
		user03.setName("test3_user");
		post03.setUser(user03);
		post03.setTimestap(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0)).getTime());
		post03.setTitle("testing3...");
		post03.setAnnounce("testing so much3");
		post03.setLikeCount(0);
		post03.setDislikeCount(0);
		post03.setCommentCount(0);
		post03.setViewCount(1);
		List<ApiPostDto> testList = new ArrayList<>();
		testList.add(post01);
		testList.add(post02);
		testList.add(post03);
		ApiPostDtoResponse res = new ApiPostDtoResponse();
		res.setCount(3);
		res.setListPosts(testList);

		mocMvc.perform(get("/api/post/").param("offset", "0").param("limit", "10").param("mode", "early"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(res)));

	}

	@Test
	void testGetAllPostsQuery() throws Exception {

		ApiPostDto post03 = new ApiPostDto();
		post03.setId(4);
		UserNoPhotoDto user03 = new UserNoPhotoDto();
		user03.setId(3);
		user03.setName("test3_user");
		post03.setUser(user03);
		post03.setTimestap(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0)).getTime());
		post03.setTitle("testing3...");
		post03.setAnnounce("testing so much3");
		post03.setLikeCount(0);
		post03.setDislikeCount(0);
		post03.setCommentCount(0);
		post03.setViewCount(1);
		List<ApiPostDto> testList = new ArrayList<>();
		testList.add(post03);
		ApiPostDtoResponse res = new ApiPostDtoResponse();
		res.setCount(1);
		res.setListPosts(testList);

		mocMvc.perform(get("/api/post/search/").param("offset", "0").param("limit", "10").param("query", "much3"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(res)));
	}
	
	@Test
	void testGetAllPostsByDate() throws Exception {

		ApiPostAltDto post03 = new ApiPostAltDto();
		post03.setId(4);
		UserNoPhotoDto user03 = new UserNoPhotoDto();
		user03.setId(3);
		user03.setName("test3_user");
		post03.setUser(user03);
		post03.setTimestap(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0)).getTime());
		post03.setTitle("testing3...");
		post03.setAnnounce("testing so much3");
		post03.setLikeCount(0);
		post03.setDislikeCount(0);
		post03.setCommentCount(0);
		post03.setViewCount(1);
		List<ApiPostAltDto> testList = new ArrayList<>();
		testList.add(post03);
		ApiPostAltDtoResponse res = new ApiPostAltDtoResponse();
		res.setCount(1);
		res.setListPosts(testList);

		mocMvc.perform(get("/api/post/byDate").param("offset", "0").param("limit", "10").param("date", "2020-01-01"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(res)));
	}


}
