package ru.afanasev.diplom.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import liquibase.pro.packaged.t;
import ru.afanasev.diplom.object.User;

@Controller
public class DefaultController {


	@GetMapping("/")
	public String defaultPage() {
		return "redirect:/index.html";
	}

}
