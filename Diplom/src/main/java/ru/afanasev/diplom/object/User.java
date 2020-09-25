package ru.afanasev.diplom.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
public class User implements UserDetails{

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

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@Setter(value = AccessLevel.NONE)
	private List<PostVote> listVotes = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@Setter(value = AccessLevel.NONE)
	private List<PostComment> listComments = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@Setter(value = AccessLevel.NONE)
	private List<Post> listPosts = new ArrayList<>();

	@OneToMany(mappedBy = "moderator")
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Roles> roles= new HashSet<Roles>();
		roles.add(Roles.USER);
		if (isModerator == 1) {
			roles.add(Roles.MODERATOR);
		}
		return roles;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
