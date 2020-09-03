package ru.afanasev.diplom.object;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "post_comments")
public class PostComment {
	
	@Id
	private Integer id;
	
	@Column(name = "parent_id", insertable = false, updatable = false)
	private Integer parentId;
	
	@Column(name = "post_id", insertable = false, updatable = false)
	private Integer postId;
	
	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer userId;
	
	private LocalDateTime time;
	
	private String text;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private PostComment postComment;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id", referencedColumnName = "id")
	private Post post;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "postComment")
	private List<PostComment> listComments;
}
