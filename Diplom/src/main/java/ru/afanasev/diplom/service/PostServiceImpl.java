package ru.afanasev.diplom.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ru.afanasev.diplom.object.ModerationStatus;
import ru.afanasev.diplom.object.Post;
import ru.afanasev.diplom.object.PostComment;
import ru.afanasev.diplom.object.Tag;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.mapper.PostMapper;
import ru.afanasev.diplom.object.dto.mapper.UserMapper;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDto;
import ru.afanasev.diplom.object.dto.postDtos.PostAltDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.PostDto;
import ru.afanasev.diplom.object.dto.postDtos.PostDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.SendPostDtoRequest;
import ru.afanasev.diplom.object.dto.postDtos.SendPostDtoResponse;
import ru.afanasev.diplom.object.dto.postDtos.SendPostErrorDtoResponse;
import ru.afanasev.diplom.object.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	private final static String TITLE_NOT_ENOUGTH_LENGTH_STRING = "Заголовок публикации слишком короткий";
	private final static String TITLE_NULL = "Заголовок не установлен";
	private final static String TEXT_NOT_ENOUGTH_LENGTH_STRING = "Текст публикации слишком короткий";
	private final static String TEXT_NULL = "Текст не установлен";

	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	@Override
	public PostDtoResponse getPosts(Integer offset, Integer limit, String mode) {

		return PostMapper.entityToApiPostDtoResponse(getListPosts(offset, limit, mode).size(),
				getListPosts(offset, limit, mode));
	}

	@Override
	public PostDtoResponse getPostsByQuery(Integer offset, Integer limit, String query) {

		return PostMapper.entityToApiPostDtoResponse(getListPostsByQuery(offset, limit, query).size(),
				getListPostsByQuery(offset, limit, query));
	}

	@Override
	public PostAltDtoResponse getPostsByDate(Integer offset, Integer limit, String query) {

		return PostMapper.entityToApiPostAltDtoResponse(getListPostsByDate(offset, limit, query).size(),
				getListPostsByDate(offset, limit, query));
	}

	@Override
	public List<PostAltDto> getPostsByTag(Integer offset, Integer limit, String query) {

		return getListPostsByTag(offset, limit, query);
	}

	@Override
	public Map<String, Integer> getCalendar(Integer[] year) {
		if (year == null) {
			Integer[] currentYear = { Calendar.getInstance().get(Calendar.YEAR) };
			return getPostsCalendar(currentYear);
		}
		return getPostsCalendar(year);
	}

	@Override
	public Post getPostById(User user, Integer id) {
		Post post = getPostById(id);
		if (user != null) {
			if (user.getIsModerator() != 1 || post.getUser().equals(user) == false) {
				increaseView(id);
			}
		} else {
			increaseView(id);
		}
		return post;
	}

	@Override
	public List<PostAltDto> getListModerationPostByModerator(User moderator, Integer offset, Integer limit,
			String status) {

		List<PostAltDto> list = new ArrayList<>();
		Pageable page = PageRequest.of(offset, limit + offset);
		String statusValue;
		switch (status) {
		case "accepted":
			statusValue = ModerationStatus.ACCEPTED.toString();
			addListAltPosts(postRepository.findModerationPostsAcceptedOrDeclined(page, ModerationStatus.ACCEPTED, moderator), list);
			break;
		case "declined":
			statusValue = ModerationStatus.DECLINED.toString();
			addListAltPosts(postRepository.findModerationPostsAcceptedOrDeclined(page, ModerationStatus.DECLINED, moderator), list);
			break;
		case "new":
			statusValue = ModerationStatus.NEW.toString();
			addListAltPosts(postRepository.findModerationPostsNew(page, ModerationStatus.NEW), list);
			break;
		}

		return list;
	}

	@Override
	@Transactional
	public SendPostDtoResponse sendPost(SendPostDtoRequest request, User user) {
		if (request.getTitle().length() < 3 || request.getText().length() < 50) {
			SendPostErrorDtoResponse sendPostErrorDto = new SendPostErrorDtoResponse();
			sendPostErrorDto.setResult(false);
			if (Utils.removeHtmlTags(request.getTitle()).length() < 3 && (Utils.removeHtmlTags(request.getTitle()).length() != 0)) {
				sendPostErrorDto.addErrors("title", TITLE_NOT_ENOUGTH_LENGTH_STRING);
			}
			if (Utils.removeHtmlTags(request.getTitle()).length() == 0) {
				sendPostErrorDto.addErrors("title", TITLE_NULL);
			}
			if (Utils.removeHtmlTags(request.getText()).length() < 3 && (Utils.removeHtmlTags(request.getText()).length() != 0)) {
				sendPostErrorDto.addErrors("text", TEXT_NOT_ENOUGTH_LENGTH_STRING);
			}
			if (Utils.removeHtmlTags(request.getText()).length() == 0) {
				sendPostErrorDto.addErrors("text", TEXT_NULL);
			}
			return sendPostErrorDto;
		} else {
			sendPostToDb(request, user);
			return new SendPostDtoResponse();
		}
	}

	@Override
	public Integer getModerationCountPost() {
		return postRepository.getCountModerationPost();
	}

	private void sendPostToDb(SendPostDtoRequest request, User user) {
		postRepository.save(PostMapper.reqToPost(request, user));
	}

	private List<PostDto> getListPosts(Integer offset, Integer limit, String mode) {

		List<PostDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset, limit + offset);
		List<Post> findPosts = new ArrayList<>();
		switch (mode) {
		case "recent":

			findPosts = postRepository.findModerationAndActivePostsSortbyRecent(page, LocalDateTime.now());
			if (findPosts.isEmpty()) {
				return listPosts;
			}
			addListPosts(findPosts, listPosts);

			break;
		case "popular":
			findPosts = postRepository.findModerationAndActivePostsSortbyPopular(page, LocalDateTime.now());
			if (findPosts.isEmpty()) {
				return listPosts;
			}
			addListPosts(findPosts, listPosts);

			break;
		case "best":
			findPosts = postRepository.findModerationAndActivePostsSortbyBest(page, LocalDateTime.now());
			if (findPosts.isEmpty()) {
				return listPosts;
			}
			addListPosts(findPosts, listPosts);

			break;
		case "early":
			findPosts = postRepository.findModerationAndActivePostsSortbyEarly(page, LocalDateTime.now());
			if (findPosts.isEmpty()) {
				return listPosts;
			}
			addListPosts(findPosts, listPosts);

			break;
		}

		return listPosts;
	}

	private List<PostDto> getListPostsByQuery(Integer offset, Integer limit, String query) {

		List<PostDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset, limit + offset);
		List<Post> findPosts = new ArrayList<>();
		if (query.isEmpty()) {
			findPosts = postRepository.findModerationAndActivePosts(page, LocalDateTime.now());
		} else {
			findPosts = postRepository.findModerationAndActivePostsByQuery(page, query, LocalDateTime.now());
			if (findPosts.isEmpty()) {
				return listPosts;
			}
		}
		addListPosts(findPosts, listPosts);

		return listPosts;
	}

	@Override
	public Set<Integer> getYears() {
		return postRepository.findAllYears();
	}

	private Map<String, Integer> getPostsCalendar(Integer[] year) {
		Map<String, Integer> countPostsYears = new TreeMap<>();
		for (Integer y : year) {
			for (String date : postRepository.findDateByYears(y)) {

				countPostsYears.put(date, postRepository.findCountPostsByDate(date));
			}
		}

		return countPostsYears;
	}

	private List<PostAltDto> getListPostsByDate(Integer offset, Integer limit, String query) {

		List<PostAltDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset, limit + offset);
		List<Post> findPosts = new ArrayList<>();
		findPosts = postRepository.findModerationAndActivePostsByDate(page, query, LocalDateTime.now());
		if (findPosts.isEmpty()) {
			return listPosts;
		}

		addListAltPosts(findPosts, listPosts);

		return listPosts;
	}

	private List<PostAltDto> getListPostsByTag(Integer offset, Integer limit, String query) {

		List<PostAltDto> listPosts = new ArrayList<>();
		Pageable page = PageRequest.of(offset, limit + offset);
		List<Post> findPosts = new ArrayList<>();
		findPosts = postRepository.findModerationAndActivePostsByTag(page, query, LocalDateTime.now());
		if (findPosts.isEmpty()) {
			return listPosts;
		}

		addListAltPosts(findPosts, listPosts);

		return listPosts;
	}

	public static String getAnnounce(String text) {

		String announce;
		if (text.length() < 200) {
			announce = text.substring(0, text.length());
			return announce;
		}
		announce = text.substring(0, 200);

		return announce;
	}

	private void addListPosts(List<Post> list, List<PostDto> listPostDto) {
		for (Post post : list) {
			listPostDto.add(PostMapper.entityToApiPostDto(UserMapper.entitytoUserNoPhotoDto(post), post));
		}
	}

	private void addListAltPosts(List<Post> list, List<PostAltDto> listPostDto) {
		for (Post post : list) {
			listPostDto.add(PostMapper.entityToApiPostAltDto(UserMapper.entitytoUserNoPhotoDto(post), post));
		}
	}

	private Post getPostById(Integer id) {
		return postRepository.findById(id).get();
	}

	@Override
	public List<PostComment> getListCommentsById(Integer id) {
		return postRepository.findCommentsByPostId(id);
	}

	@Override
	public List<String> getTagByPostId(Integer id) {
		List<String> tags = new ArrayList<>();
		for (Tag tag : postRepository.findTagsByPostId(id)) {
			System.out.println(tag.getName());
			tags.add(tag.getName());
		}
		return tags;
	}

	private void increaseView(Integer id) {
		postRepository.increaseViewCount(id);
	}

}
