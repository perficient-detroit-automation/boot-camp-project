package com.perficient.spring.web.controller;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.perficient.spring.web.dao.Candidate;
import com.perficient.spring.web.dao.CandidateService;
import com.perficient.spring.web.dao.EnumTableRow;


@Controller
public final class HomeController {

	static Logger log = Logger.getLogger(HomeController.class);

	@Autowired
	private CandidateService service;


	@ModelAttribute("statuses")
	public List<EnumTableRow> populateStatuses() {
		List<EnumTableRow> statuses = new ArrayList<EnumTableRow>(4);

		statuses.add(new EnumTableRow(0, "Interviewing"));
		statuses.add(new EnumTableRow(1, "Waiting on response"));
		statuses.add(new EnumTableRow(2, "Discontinued pursuit"));
		statuses.add(new EnumTableRow(3, "Hired"));

		return statuses;
	}

	@ModelAttribute("degrees")
	public List<EnumTableRow> populateDegrees() {
		List<EnumTableRow> degrees = new ArrayList<EnumTableRow>(5);

		degrees.add(new EnumTableRow(0, "High school diploma / GED"));
		degrees.add(new EnumTableRow(1, "Certificate"));
		degrees.add(new EnumTableRow(2, "Associate's degree"));
		degrees.add(new EnumTableRow(3, "Bachelor's degree"));
		degrees.add(new EnumTableRow(4, "Master's degree"));
		degrees.add(new EnumTableRow(5, "Doctorate"));

		return degrees;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showThymeleafPage(Model model) {
		model.addAttribute("candidate", service.getSampleCandidate());
		return "candidate-thymeleaf";
	}

	@RequestMapping(value = "/", params = "save", method = RequestMethod.POST)
	public String saveMethod(Candidate candidate) {
		//Save button implementation for Candidate Service goes here
		log.isInfoEnabled();
		log.info(candidate.getFirstName());
		log.info(candidate.getLastName());
		log.info(candidate.getPhoneNumber());
		log.info(candidate.getEmailAddress());
		log.info(candidate.getStatus());
		log.info(candidate.getDegree());
		log.info(candidate.getGraduationDate());
		log.info(candidate.getMajor());
		log.info(candidate.getSkillset());
		log.info(candidate.getComments());
		log.info(candidate);
		log.info("TESTTTTTTTTTTTTTTTT");
		return "candidate-thymeleaf";
		//return "redirect:/";
	}

	@RequestMapping(value = "/", params = "convert", method = RequestMethod.POST)
	public String convertMethod() {
		//Convert to employee button implementation for Candidate Service goes here
		return "home2";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class,
			new CustomDateEditor(dateFormat, false));
	}
}