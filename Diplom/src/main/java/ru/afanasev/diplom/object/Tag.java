package ru.afanasev.diplom.object;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tags")
public class Tag {
	
	@Id
	private Integer id;
	
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tag2post",
	joinColumns = {@JoinColumn(name = "tag_id")},
	inverseJoinColumns = {@JoinColumn(name = "post_id")})
	private List<Post> listPosts;
}
