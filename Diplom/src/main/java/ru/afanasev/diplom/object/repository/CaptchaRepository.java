package ru.afanasev.diplom.object.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ru.afanasev.diplom.object.CaptchaCode;

public interface CaptchaRepository extends JpaRepository<CaptchaCode,Integer>{
	
	@Modifying
	@Query(value = "insert into captcha_codes (time, code, secret_code) values (?2, ?1, ?3)", nativeQuery = true)
	void insertCaptcha (String code, LocalDateTime time,String secretCode);
	@Modifying
	@Query(value = "delete from CaptchaCode c where c.secretCode = ?1")
	void deleteBySecretCode(String secretCode);
	
	@Modifying
	@Query(value = "delete from CaptchaCode c where c.time >= ?1")
	void deleteBySecretCodeV2(LocalDateTime time);
}
