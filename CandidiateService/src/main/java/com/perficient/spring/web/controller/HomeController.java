package com.perficient.spring.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perficient.spring.web.dao.CandidateService;


@Controller
public final class HomeController {

	@Autowired
	private CandidateService service;


	@RequestMapping("/")
	public String showThymeleafPage(Model model) {
		model.addAttribute("candidate", service.getSampleCandidate());
		return "candidate-thymeleaf";
	}
}
