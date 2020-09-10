package ru.afanasev.diplom.object;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "tags")
@Data
public class Tag {
	
	@Id
	private Integer id;
	
	private String name;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tag2post",
	joinColumns = {@JoinColumn(name = "tag_id")},
	inverseJoinColumns = {@JoinColumn(name = "post_id")})
	@Setter(value = AccessLevel.NONE)
	private List<Post> listPosts;
	
	public void addPost(Post post) {
		listPosts.add(post);
		post.getListTags().add(this);
	}
	
	public void removePost(Post post) {
		listPosts.remove(post);
		post.getListTags().remove(this);
	}
}
