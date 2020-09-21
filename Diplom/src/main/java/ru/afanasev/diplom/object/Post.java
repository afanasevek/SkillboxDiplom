package ru.afanasev.diplom.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Entity
@Table(name = "posts")
@Data
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
	
	@Setter(value = AccessLevel.NONE)
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "tag2post",
	joinColumns = {@JoinColumn(name = "post_id")},
	inverseJoinColumns = {@JoinColumn(name = "tag_id")})
	private Set<Tag> listTags = new LinkedHashSet<>();
	
	@Setter(value = AccessLevel.NONE)
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<PostVote> listVotes = new LinkedHashSet<>();
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@Setter(value = AccessLevel.NONE)
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
}
