package ru.afanasev.diplom.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.afanasev.diplom.object.DTO.InitDtoResponse;

@Controller
@PropertySource("classpath:application.yml")
@RequestMapping("/api")
public class ApiGeneralController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${constant.init.title}")
	private String title;

	@Value("${constant.init.subtitle}")
	private String subtitle;

	@Value("${constant.init.phone}")
	private String phone;

	@Value("${constant.init.email}")
	private String email;

	@Value("${constant.init.copyright}")
	private String copyright;

	@Value("${constant.init.copyrightFrom}")
	private String copyrightFrom;

	@GetMapping("/")
	public String defaultPage() {
		return "redirect:/index.html";
	}
	
	@GetMapping("/init/")
	ResponseEntity<InitDtoResponse> init() {
		try {
			InitDtoResponse init = new InitDtoResponse(
					title,
					subtitle,
					phone,
					email,
					copyright,
					copyrightFrom
					);
			
			return ResponseEntity.ok(init);
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
