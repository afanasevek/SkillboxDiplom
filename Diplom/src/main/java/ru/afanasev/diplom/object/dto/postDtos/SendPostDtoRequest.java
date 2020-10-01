package ru.afanasev.diplom.object.dto.postDtos;

import java.sql.Date;

public class SendPostDtoRequest {
	private Long timestamp;
	private Byte active;
	private String title;
	private String[] tags;
	private String text;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
