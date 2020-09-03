package ru.afanasev.diplom.object;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "is_active")
	private Byte isActive;

	@Enumerated(EnumType.STRING)
	@Column(name = "moderation_status")
	private ModerationStatus moderationStatus;

	@Column(name = "moderator_id", insertable = false, updatable = false)
	private Integer moderatorId;

	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer userId;

	private LocalDateTime time;
	@Column(name = "title")
	private String title;

	@Column(name = "view_count")
	private Integer viewCount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "moderator_id", referencedColumnName = "id")
	private User moderator;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tag2post",
	joinColumns = {@JoinColumn(name = "post_id")},
	inverseJoinColumns = {@JoinColumn(name = "tag_id")})
	private List<Tag> listTags;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	private List<PostVote> listVotes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	private List<PostComment> listComments;

}
