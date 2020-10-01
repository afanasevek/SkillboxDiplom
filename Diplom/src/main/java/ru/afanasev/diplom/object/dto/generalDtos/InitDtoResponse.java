package ru.afanasev.diplom.object.dto.generalDtos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class InitDtoResponse {

	private String title;
	private String subtitle;
	private String phone;
	private String email;
	private String copyright;
	private String copyrightFrom;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getCopyrightFrom() {
		return copyrightFrom;
	}
	public void setCopyrightFrom(String copyrightFrom) {
		this.copyrightFrom = copyrightFrom;
	}

	
}
