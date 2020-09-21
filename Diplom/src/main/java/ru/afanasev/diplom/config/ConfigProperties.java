package ru.afanasev.diplom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import ru.afanasev.diplom.object.dto.generalDtos.InitDtoResponse;

@Configuration
public class ConfigProperties {

	@Bean
	@ConfigurationProperties(prefix = "init")
	public InitDtoResponse  getInit () {
		return new InitDtoResponse();
	}
	
}
