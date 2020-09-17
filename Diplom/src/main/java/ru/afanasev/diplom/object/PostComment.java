package ru.afanasev.diplom.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Entity
@Table(name = "post_comments")
public class PostComment {

	@Id
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private PostComment postComment;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id", referencedColumnName = "id")
	private Post post;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	private LocalDateTime time;

	private String text;

	@OneToMany(mappedBy = "postComment")
	@Setter(value = AccessLevel.NONE)
	private List<PostComment> listComments = new ArrayList<>();

	public void addPostComment(PostComment postComment) {
		listComments.add(postComment);
		postComment.setPostComment(this);
	}

	public void removePostComment(PostComment postComment) {
		listComments.remove(postComment);
		postComment.setPostComment(null);
	}
}
