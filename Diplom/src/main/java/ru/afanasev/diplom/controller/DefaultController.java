package ru.afanasev.diplom.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/")
	public String defaultPage() {
		return "redirect:/index.html";
	}

}
