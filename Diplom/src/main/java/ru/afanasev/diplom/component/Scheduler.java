package ru.afanasev.diplom.component;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



import ru.afanasev.diplom.object.repository.CaptchaRepository;

@Component
public class Scheduler {
	
	private  final CaptchaRepository captchaRepository;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);	
    
	public Scheduler(CaptchaRepository captchaRepository) {
		super();
		this.captchaRepository = captchaRepository;
	}



	@Transactional
	@Scheduled(fixedRate = 3600000)
	public void deleteCaptcha() {

		captchaRepository.deleteBySecretCodeV2(LocalDateTime.now().minusHours(1));
		LOGGER.info("Delete old captcha...");
	}
	
}
