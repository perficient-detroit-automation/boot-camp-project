package com.perficient.spring.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.perficient.spring.web.dao.CandidateService;


@Controller
public final class HomeController {

	@Autowired
	private CandidateService service;


	@RequestMapping(value="/",method=RequestMethod.GET)
	public String showThymeleafPage(Model model) {
		model.addAttribute("candidate", service.getSampleCandidate());
		return "candidate-thymeleaf";
	}
	
	@RequestMapping(value="/",params="save",method=RequestMethod.POST)
	public String saveMethod() {
		//Save button implementation for Candidate Service goes here
		return "candidate-thymeleaf";
	}
	
	@RequestMapping(value="/",params="convert",method=RequestMethod.POST)
	public String convertMethod() {
		//Convert to employee button implementation for Candidate Service goes here
		return "home2";
	}
}
