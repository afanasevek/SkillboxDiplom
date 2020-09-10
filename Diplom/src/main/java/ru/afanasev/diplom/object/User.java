package ru.afanasev.diplom.object;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity	
@Table(name = "users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "is_moderator")
	private Byte isModerator;
	
	@Column(name = "reg_time")
	private LocalDateTime regTime;
	
	private String name;
	
	private String email;
	
	private String password;
	

	private String code;
	
	private String photo;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	@Setter(value = AccessLevel.NONE)
	private List<PostVote> listVotes;
	

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	@Setter(value = AccessLevel.NONE)
	private List<PostComment> listComments;
	

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	@Setter(value = AccessLevel.NONE)
	private List<Post> listPosts;
	

	@OneToMany(mappedBy = "moderator", cascade = CascadeType.PERSIST)
	@Setter(value = AccessLevel.NONE)
	private List<Post> listModPosts;
	
	public void addVote(PostVote postVote) {
		listVotes.add(postVote);
		postVote.setUser(this);
	}
	public void removeVote(PostVote postVote) {
		listVotes.remove(postVote);
		postVote.setUser(null);
	}
	public void addComment(PostComment postComment) {
		listComments.add(postComment);
		postComment.setUser(this);
	}
	public void removeComment(PostComment postComment) {
		listComments.remove(postComment);
		postComment.setUser(null);
	}
	public void addPost(Post post) {
		listPosts.add(post);
		post.setUser(this);
	}
	public void removePost(Post post) {
		listPosts.remove(post);
		post.setUser(null);
	}
	public void addModPost(Post post) {
		listModPosts.add(post);
		post.setUser(this);
	}
	public void removeModPost(Post post) {
		listModPosts.remove(post);
		post.setUser(null);
	}
}
