package ru.afanasev.diplom.object;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "captcha_codes")
public class CaptchaCode {

	@Id
	private Integer id;

	private LocalDateTime time;

	private String code;

	@Column(name = "secret_code")
	private String secretCode;
}
