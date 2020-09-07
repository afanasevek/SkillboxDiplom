package ru.afanasev.diplom.object;

import java.time.LocalDateTime;
import java.util.List;

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

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Integer id;

	@Getter
	@Setter
	@Column(name = "is_active")
	private Byte isActive;

	
	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	@Column(name = "moderation_status")
	private ModerationStatus moderationStatus;

	
	@Getter
	@Setter
	@ManyToOne()
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@Getter
	@Setter
	@ManyToOne()
	@JoinColumn(name = "moderator_id", referencedColumnName = "id")
	private User moderator;

	@Getter
	@Setter
	private LocalDateTime time;
	
	@Getter
	@Setter
	@Column(name = "title")
	private String title;
	
	@Getter
	@Setter
	@Column(name = "text")
	private String text;

	@Getter
	@Setter
	@Column(name = "view_count")
	private Integer viewCount;
	
	@Getter
	@ManyToMany()
	@JoinTable(name = "tag2post",
	joinColumns = {@JoinColumn(name = "post_id")},
	inverseJoinColumns = {@JoinColumn(name = "tag_id")})
	private List<Tag> listTags;
	
	@Getter
	@OneToMany(mappedBy = "post")
	private List<PostVote> listVotes;
	
	@Getter
	@OneToMany(mappedBy = "post")
	private List<PostComment> listComments;

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
		postComment.setPost(this);
	}
	
	public void removePostComment(PostComment postComment) {
		listComments.remove(postComment);
		postComment.setPost(this);
	}
}
