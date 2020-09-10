package ru.afanasev.diplom.object.DTO;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class InitDtoResponse {
	
	private String title;
	private String subtitle;
	private String phone;
	private String email;
	private String copyright;
	private String copyrightFrom;

}
