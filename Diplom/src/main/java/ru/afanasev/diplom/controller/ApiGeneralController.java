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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.afanasev.diplom.config.ConfigProperties;
import ru.afanasev.diplom.object.dto.generalDtos.CalendarDtoResponse;
import ru.afanasev.diplom.object.dto.generalDtos.InitDtoResponse;
import ru.afanasev.diplom.object.dto.mapper.CalendarMapper;
import ru.afanasev.diplom.object.dto.mapper.PostMapper;
import ru.afanasev.diplom.object.dto.mapper.TagMapper;
import ru.afanasev.diplom.object.dto.tagDtos.TagDtoResponse;
import ru.afanasev.diplom.service.PostService;
import ru.afanasev.diplom.service.TagService;
import ru.afanasev.diplom.service.TagServiceImpl;

@Controller
@RequestMapping("/api")
public class ApiGeneralController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	private final ConfigProperties configProperties;
	
	private final PostService postService;
	
	private final TagService tagService;
	
	public ApiGeneralController(
			PostService postService,
			TagService tagService,
			ConfigProperties configProperties
			) {
		this.configProperties =configProperties;
		this.tagService = tagService;
		this.postService = postService;
	}


	@PreAuthorize("permitAll()")
	@GetMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public InitDtoResponse init() {

		return configProperties.getInit();

	}

	@PreAuthorize("permitAll()")
	@GetMapping(value = "/calendar/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CalendarDtoResponse getCalendar(@RequestParam(required = false) Integer[] year) {
		
		return CalendarMapper.getCalendarDtoResponse(postService.getYears(),
				postService.getCalendar(year));
	}

	@PreAuthorize("permitAll()")
	@GetMapping(value = "/tag/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TagDtoResponse getTag(@RequestParam(required = false) String tag) {
		
		return TagMapper.entityToTagDtoResponse(tagService.getAllweight(tag));
	
	}
}
