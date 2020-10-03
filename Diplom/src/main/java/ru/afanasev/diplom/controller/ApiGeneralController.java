package ru.afanasev.diplom.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.afanasev.diplom.config.ConfigProperties;
import ru.afanasev.diplom.object.dto.generalDtos.CalendarDtoResponse;
import ru.afanasev.diplom.object.dto.generalDtos.InitDtoResponse;
import ru.afanasev.diplom.object.dto.mapper.CalendarMapper;
import ru.afanasev.diplom.object.dto.mapper.SettingsMapper;
import ru.afanasev.diplom.object.dto.mapper.TagMapper;
import ru.afanasev.diplom.object.dto.tagDtos.TagDtoResponse;
import ru.afanasev.diplom.service.PostService;
import ru.afanasev.diplom.service.SettingsService;
import ru.afanasev.diplom.service.TagService;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ConfigProperties configProperties;

	private final PostService postService;

	private final TagService tagService;

	private final SettingsService settingsService;

	public ApiGeneralController(PostService postService, TagService tagService, ConfigProperties configProperties,
			SettingsService settingsService) {
		this.configProperties = configProperties;
		this.tagService = tagService;
		this.postService = postService;
		this.settingsService = settingsService;
	}

	@GetMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
	public InitDtoResponse init() {

		return configProperties.getInit();

	}

	@GetMapping(value = "/calendar/", produces = MediaType.APPLICATION_JSON_VALUE)
	public CalendarDtoResponse getCalendar(@RequestParam(required = false) Integer[] year) {

		return CalendarMapper.getCalendarDtoResponse(postService.getYears(), postService.getCalendar(year));
	}

	@GetMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_VALUE)
	public TagDtoResponse getTag(@RequestParam(required = false) String tag) {

		return TagMapper.entityToTagDtoResponse(tagService.getAllweight(tag));

	}

	@GetMapping(value = "/settings", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Boolean> getSettings() {
		return SettingsMapper.entityToSettingsDtoResponse(settingsService.getGlobalSettings());
	}
}
