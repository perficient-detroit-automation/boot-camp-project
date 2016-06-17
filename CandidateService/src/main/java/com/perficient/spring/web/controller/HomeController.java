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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.perficient.spring.web.model.Candidate;
import com.perficient.spring.web.model.EnumTableRow;
import com.perficient.spring.web.service.CandidateService;


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
	public String showThymeleafPage() {
		return "redirect:/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCandidate(Model model) {
		model.addAttribute("candidate", new Candidate()); // replace service.getSampleCandidate() with new Candidate(), testing rn
		return "candidate-thymeleaf-add";
	}
	
	@RequestMapping(value = "/add", params = "add", method = RequestMethod.POST)
	public String addCandidate(Candidate candidate, RedirectAttributes redirectAttributes) {
		System.out.println("In the /add POST requestmapping");
		redirectAttributes.addFlashAttribute("candidate", service.addcandidate(candidate));
		return "redirect:/edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String EditCandidate(Candidate candidate, Model model) {
		model.addAttribute("candidate", candidate);
		return "candidate-thymeleaf";
	}

	@RequestMapping(value = "/edit", params = "save", method = RequestMethod.POST)
	public String saveMethod(Candidate candidate, Model model) {
		//Save button implementation for Candidate Service goes here
		model.addAttribute("candidate", service.saveCandidate(candidate));
		return "candidate-thymeleaf";
	}

	@RequestMapping(value = "/edit", params = "convert", method = RequestMethod.POST)
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

	@RequestMapping("/home")
	public String getOneCandidate(Model model) {
		model.addAttribute("candidate", service.getOneCandidate()); // gives retrieved candidate object to home.html
		return "candidate-thymeleaf";
	}
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "text/plain")
	public ArrayList<String> findList(@RequestBody String searchBar) {
		System.out.println("in /search");
//		ArrayList<String> a = new ArrayList<String>();
//		a.add(searchBar);
//		return a;
		return service.findAll(searchBar);
	}
}
