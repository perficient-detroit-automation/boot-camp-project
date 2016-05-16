package com.perficient.spring.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public final class HomeController {

	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
}
