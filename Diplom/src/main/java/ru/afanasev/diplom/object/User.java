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

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity	
@Table(name = "users")
public class User {
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Getter
	@Setter
	@Column(name = "is_moderator")
	private Byte isModerator;
	
	@Getter
	@Setter
	@Column(name = "reg_time")
	private LocalDateTime regTime;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	private String code;
	
	@Getter
	@Setter
	private String photo;
	
	@Getter
	@OneToMany(mappedBy = "user")
	private List<PostVote> listVotes;
	
	@Getter
	@OneToMany(mappedBy = "user")
	private List<PostComment> listComments;
	
	@Getter
	@OneToMany(mappedBy = "user")
	private List<Post> listPosts;
	
	@Getter
	@OneToMany(mappedBy = "moderator")
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
