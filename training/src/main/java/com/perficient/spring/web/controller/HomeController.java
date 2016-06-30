package com.perficient.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.perficient.spring.web.model.DropdownOption;
import com.perficient.spring.web.model.TrainingMaster;
import com.perficient.spring.web.service.TrainingService;

@Controller
public class HomeController {
	
	@Autowired
	TrainingService service;
	
	@ModelAttribute("durationUnits")
	public List <DropdownOption> addDurationUnits(){
		
		List <DropdownOption> durationUnits= new ArrayList<DropdownOption>(3);
		durationUnits.add(new DropdownOption(0, "Hours"));
		durationUnits.add(new DropdownOption(1, "Days"));
		durationUnits.add(new DropdownOption(2, "Weeks"));
		
		return durationUnits;
	}
	
	@ModelAttribute("trainingStatuses")
	public List <DropdownOption> addTrainingStatuses(){
		
		List <DropdownOption> trainingStatuses= new ArrayList<DropdownOption>(3);
		trainingStatuses.add(new DropdownOption(0, "Active"));
		trainingStatuses.add(new DropdownOption(1, "Inactive"));
		trainingStatuses.add(new DropdownOption(2, "On-hold"));
		
		return trainingStatuses;
	}
	
	
	@ModelAttribute("bootcampTypes")
	public List <DropdownOption> addBootcampTypes(){
		
		List <DropdownOption> bootcampTypes= new ArrayList<DropdownOption>(4);
		bootcampTypes.add(new DropdownOption(0, "Development"));
		bootcampTypes.add(new DropdownOption(1, "Testing"));
		bootcampTypes.add(new DropdownOption(2, "Automation"));
		bootcampTypes.add(new DropdownOption(3, "Management"));
		
		return bootcampTypes;
	}
	
	
	@RequestMapping(value="/")
	public String showHome(){
	
		return "redirect:/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showAddPage(Model model){
		model.addAttribute("trainingMaster", new TrainingMaster());	
		return"trainingMaster";	
	}
	
	@RequestMapping(value="/add", params= "add", method=RequestMethod.POST)
	public String addPOST(TrainingMaster tm, RedirectAttributes re){
		System.out.println("Posted");
		re.addAttribute("id", service.addTraining(tm));	
		return"redirect:/edit";	
	}
	
	@RequestMapping(value= "/edit", method=RequestMethod.GET)
	public String showEditPage(@RequestParam("id")int id, Model model){
		
		model.addAttribute("trainingMaster", new TrainingMaster());
		System.out.println("id:" + id);
		
		return "trainingMaster";
		
	}
}
