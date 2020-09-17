package ru.afanasev.diplom.controller;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.afanasev.diplom.config.ConfigProperties;
import ru.afanasev.diplom.object.DTO.CalendarDtoResponse;
import ru.afanasev.diplom.object.DTO.InitDtoResponse;
import ru.afanasev.diplom.object.DTO.mapper.PostMapper;
import ru.afanasev.diplom.service.PostService;

@Controller
@RequestMapping("/api")
public class ApiGeneralController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConfigProperties configProperties;
	@Autowired
	private PostService postService;

	@GetMapping("/")
	public String defaultPage() {
		return "redirect:/index.html";
	}

	@GetMapping(value = "/init/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public InitDtoResponse init() {

		return configProperties.getInit();

	}

	@GetMapping(value = "/calendar/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CalendarDtoResponse getCalendar(@RequestParam(required = false) Integer[] year) {
		
		return postService.getCalendar(year);
	}
}
