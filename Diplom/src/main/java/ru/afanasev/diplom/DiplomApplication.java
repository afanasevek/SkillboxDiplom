package ru.afanasev.diplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import ru.afanasev.diplom.config.ConfigProperties;
import ru.afanasev.diplom.object.DTO.InitDtoResponse;

@SpringBootApplication
public class DiplomApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiplomApplication.class, args);
	}

}
