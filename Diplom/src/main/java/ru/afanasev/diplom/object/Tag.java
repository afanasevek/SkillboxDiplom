package ru.afanasev.diplom.object;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@ManyToMany(mappedBy = "listTags")
	private List<Post> listPosts = new ArrayList<>();

	public void addPost(Post post) {
		listPosts.add(post);
		post.getListTags().add(this);
	}

	public void removePost(Post post) {
		listPosts.remove(post);
		post.getListTags().remove(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getListPosts() {
		return listPosts;
	}

}
