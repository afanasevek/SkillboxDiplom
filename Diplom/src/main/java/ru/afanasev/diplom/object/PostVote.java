package ru.afanasev.diplom.object;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "post_votes")
public class PostVote {
	
	@Id
	private Integer id;
	
	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer userId;
	
	@Column(name = "post_id", insertable = false, updatable = false)
	private Integer postId;
	
	private LocalDateTime time;
	
	private Byte value;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "post_id", referencedColumnName = "id")
	private Post post;
	
}
