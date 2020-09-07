package ru.afanasev.diplom.object.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class InitDtoResponse {
	
	private String title;
	private String subtitle;
	private String phone;
	private String email;
	private String copyright;
	private String copyrightFrom;

}
