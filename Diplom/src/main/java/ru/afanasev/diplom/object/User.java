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

@Data
@Entity	
@Table(name = "users")
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<PostVote> listVotes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<PostComment> listComments;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Post> listPosts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "moderator")
	private List<Post> listModPosts;
}
