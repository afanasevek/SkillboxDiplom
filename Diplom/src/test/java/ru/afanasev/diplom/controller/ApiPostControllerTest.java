package ru.afanasev.diplom.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import ru.afanasev.diplom.object.ModerationStatus;
import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostVote;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.DTO.ApiPostDto;
import ru.afanasev.diplom.object.DTO.ApiPostDtoResponse;
import ru.afanasev.diplom.object.DTO.UserNoPhotoDto;
import ru.afanasev.diplom.object.repository.PostRepository;
import ru.afanasev.diplom.object.repository.PostVoteRepository;
import ru.afanasev.diplom.object.repository.UserRepository;
import ru.afanasev.diplom.service.PostService;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ApiPostControllerTest {
	
	@Autowired
	private MockMvc mocMvc;
	
	@Autowired
	private ApiPostController apiPostController;
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostVoteRepository postVoteRepository;
	
	ApiPostDtoResponse apiPostDtoResponse;
	
	@BeforeEach
	public void setUp() {		

		User user = new User();
		user.setId(1);
		user.setName("test_user");
		user.setEmail("test@test.com");
		user.setPassword("test");
		user.setIsModerator((byte)1);
		user.setRegTime(LocalDateTime.now().minusMonths(4).minusDays(12));
		userRepository.save(user);
		
		Post post = new Post();
		post.setId(1);
		post.setIsActive((byte)1);
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
		postVote.setValue((byte)1);
		postVote.setTime(LocalDateTime.now().minusMonths(1).minusDays(10));
		postVoteRepository.save(postVote);		

		User user1 = new User();
		user1.setId(2);
		user1.setName("test_user2");
		user1.setEmail("test2@test.com");
		user1.setPassword("test2");
		user1.setIsModerator((byte)1);
		user1.setRegTime(LocalDateTime.now().minusMonths(10).minusDays(2));
		userRepository.save(user1);
		
		Post post1 = new Post();
		post1.setId(2);
		post1.setIsActive((byte)1);
		post1.setModerationStatus(ModerationStatus.ACCEPTED);
		post1.setUser(user1);
		post1.setModerator(user1);
		post1.setTitle("testing 2..");
		post1.setText("testing so much2");
		post1.setViewCount(15000);
		post1.setTime(LocalDateTime.of(1990, 1, 1, 0, 0, 0));
		postRepository.save(post1);
		
		PostVote postVote1 = new PostVote();
		postVote1.setUser(user1);
		postVote1.setId(2);
		postVote1.setPost(post1);
		postVote1.setValue((byte)1);
		postVote1.setTime(LocalDateTime.now().minusMonths(1).minusDays(10));
		postVoteRepository.save(postVote1);
		
		User user2 = new User();
		user2.setId(3);
		user2.setName("test3_user");
		user2.setEmail("test3@test.com");
		user2.setPassword("test3");
		user2.setIsModerator((byte)1);
		user2.setRegTime(LocalDateTime.now().minusMonths(4).minusDays(12));
		userRepository.save(user2);
				
		Post post2 = new Post();
		post2.setId(3);
		post2.setIsActive((byte)-1);
		post2.setModerationStatus(ModerationStatus.ACCEPTED);
		post2.setUser(user2);
		post2.setModerator(user2);
		post2.setTitle("testing3...");
		post2.setText("testing so much3");
		post2.setViewCount(1510);
		post2.setTime(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
		postRepository.save(post2);

		
		PostVote postVote2 = new PostVote();
		postVote2.setPost(post2);
		postVote2.setId(3);
		postVote2.setUser(user2);
		postVote2.setValue((byte)1);
		postVote2.setTime(LocalDateTime.now().minusMonths(1).minusDays(10));
		postVoteRepository.save(postVote2);
		

//		postVoteRepository.save(postVote2);

//		apiPostDtoResponse = new ApiPostDtoResponse();
		
//		 List<ApiPostDto> testList = new ArrayList();
//		 
//		 ApiPostDto apiPostDto = new ApiPostDto();
//		 apiPostDto.setId(1);
//		 apiPostDto.setTimestap(Timestamp.valueOf(LocalDateTime.of(2005, 1, 1, 0, 0, 0)).getTime());
//		 UserNoPhotoDto user_testDto = new UserNoPhotoDto();
//		 user_testDto.setIdInteger(1);
//		 user_testDto.setName("test_user");
//		 apiPostDto.setUser(user_testDto);
//		 apiPostDto.setTitle("testing...");
//		 apiPostDto.setAnnounce("testing so much");
//		 apiPostDto.setLikeCount(1);
//		 apiPostDto.setDislikeCount(0);
//		 apiPostDto.setViewCount(150);
		 
//		 ApiPostDto apiPostDto1 = new ApiPostDto();
//		 apiPostDto1.setId(2);
//		 apiPostDto1.setTimestap(Timestamp.valueOf(LocalDateTime.of(1990, 1, 1, 0, 0, 0)).getTime());
//		 UserNoPhotoDto user_testDto1 = new UserNoPhotoDto();
//		 user_testDto1.setIdInteger(2);
//		 user_testDto1.setName("test2_user");
//		 apiPostDto1.setUser(user_testDto1);
//		 apiPostDto1.setTitle("testing 2..");
//		 apiPostDto1.setAnnounce("testing so much2");
//		 apiPostDto1.setLikeCount(1);
//		 apiPostDto1.setDislikeCount(0);
//		 apiPostDto1.setViewCount(15000);
//		 
//		 testList.add(apiPostDto);
//		 testList.add(apiPostDto1);
//		 
//		 apiPostDtoResponse.setCount(2);
//		 apiPostDtoResponse.setListPosts(testList);
	}
	
	@Test
	void testGetAllPosts() throws Exception {
			 
		mocMvc.perform(get("/api/post/").param("offset", "0").param("limit", "10").param("mode", "recent")).andDo(print())
		.andExpect(status().isOk());
	
	}

}
