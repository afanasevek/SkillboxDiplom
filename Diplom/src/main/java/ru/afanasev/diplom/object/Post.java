package ru.afanasev.diplom.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "is_active")
	private Byte isActive;

	@Enumerated(EnumType.STRING)
	@Column(name = "moderation_status")
	private ModerationStatus moderationStatus;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "moderator_id", referencedColumnName = "id")
	private User moderator;

	private LocalDateTime time;

	@Column(name = "title")
	private String title;

	@Column(name = "text")
	private String text;

	@Column(name = "view_count")
	private Integer viewCount;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "tag2post", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = {
			@JoinColumn(name = "tag_id") })
	private List<Tag> listTags = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostVote> listVotes = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostComment> listComments = new ArrayList<>();

	public void addTag(Tag tag) {
		listTags.add(tag);
		tag.getListPosts().add(this);
	}

	public void removeTag(Tag tag) {
		listTags.remove(tag);
		tag.getListPosts().remove(this);
	}

	public void addPostVote(PostVote postVote) {
		listVotes.add(postVote);
		postVote.setPost(this);
	}

	public void removePostVote(PostVote postVote) {
		listVotes.remove(postVote);
		postVote.setPost(this);
	}

	public void addPPostComment(PostComment postComment) {
		listComments.add(postComment);
		postComment.setPost(null);
	}

	public void removePostComment(PostComment postComment) {
		listComments.remove(postComment);
		postComment.setPost(null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModerationStatus getModerationStatus() {
		return moderationStatus;
	}

	public void setModerationStatus(ModerationStatus moderationStatus) {
		this.moderationStatus = moderationStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getModerator() {
		return moderator;
	}

	public void setModerator(User moderator) {
		this.moderator = moderator;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	public List<Tag> getListTags() {
		return listTags;
	}

	public List<PostVote> getListVotes() {
		return listVotes;
	}

	public List<PostComment> getListComments() {
		return listComments;
	}

}
