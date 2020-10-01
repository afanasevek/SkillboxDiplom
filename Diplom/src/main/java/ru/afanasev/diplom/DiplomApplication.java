package ru.afanasev.diplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import ru.afanasev.diplom.config.ConfigProperties;
import ru.afanasev.diplom.object.dto.generalDtos.InitDtoResponse;

@SpringBootApplication
@EnableScheduling
public class DiplomApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiplomApplication.class, args);
	}

}
