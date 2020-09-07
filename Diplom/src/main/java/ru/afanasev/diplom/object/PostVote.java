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
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "post_votes")
public class PostVote {
	
	@Id
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name= "post_id", referencedColumnName = "id")
	private Post post;
	
	private LocalDateTime time;
	
	private Byte value;
	

	
}
