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
import org.thymeleaf.util.StringUtils;

import com.perficient.spring.web.model.DropdownOption;
import com.perficient.spring.web.model.Search;
import com.perficient.spring.web.model.TrainingMaster;
import com.perficient.spring.web.service.TrainingService;

@Controller
@RequestMapping(value="/trainingMaster")
public class TrainingMasterController {
	
	@Autowired
	TrainingService service;
	
	@ModelAttribute("durationUnits")
	public List <DropdownOption> addDurationUnits(){
		
		List <DropdownOption> durationUnits= new ArrayList<DropdownOption>(3);
		durationUnits.add(new DropdownOption(1, "Hours"));
		durationUnits.add(new DropdownOption(2, "Days"));
		durationUnits.add(new DropdownOption(3, "Weeks"));
		
		return durationUnits;
	}
	
	@ModelAttribute("trainingStatuses")
	public List <DropdownOption> addTrainingStatuses(){
		
		List <DropdownOption> trainingStatuses= new ArrayList<DropdownOption>(3);
		trainingStatuses.add(new DropdownOption(1, "Active"));
		trainingStatuses.add(new DropdownOption(2, "Inactive"));
		trainingStatuses.add(new DropdownOption(3, "On-hold"));
		
		return trainingStatuses;
	}
	
	
	@ModelAttribute("bootcampTypes")
	public List <DropdownOption> addBootcampTypes(){
		
		List <DropdownOption> bootcampTypes= new ArrayList<DropdownOption>(4);
		bootcampTypes.add(new DropdownOption(1, "Development"));
		bootcampTypes.add(new DropdownOption(2, "Testing"));
		bootcampTypes.add(new DropdownOption(3, "Automation"));
		bootcampTypes.add(new DropdownOption(4, "Management"));
		
		return bootcampTypes;
	}
	
	
	@RequestMapping(value="")
	public String showHome(){
	
		return "redirect:/trainingMaster/add";
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
		return"redirect:/trainingMaster/edit";	
	}
	
	@RequestMapping(value= "/edit", method=RequestMethod.GET)
	public String showEditPage(@RequestParam("id")int id, Model model){
		
		model.addAttribute("trainingMaster", service.findTraining(id));
		System.out.println("id:" + id);
		
		return "trainingMaster-edit";
		
	}
	
	@RequestMapping(value= "/edit", method=RequestMethod.POST)
	public String showPostPage(TrainingMaster tm, Model model){
		
		model.addAttribute("trainingMaster", service.saveTraining(tm));
		
		return "trainingMaster-edit";
		
	}
	
	@RequestMapping(value= "/search", method=RequestMethod.GET)
	public String showSearchPage(Model model){
		
		model.addAttribute ("search", new Search());
		
		return "searchTrainingMaster";
		
	}
	
	@RequestMapping(value= "/search", params= "execute", method=RequestMethod.POST)
	public String showSearch(@RequestParam ("searchBar") String searchBar, Model model){
		
		System.out.println("Search Bar" + searchBar);
		ArrayList<String> test=new ArrayList<String>();
		test= service.findAll(searchBar);
		List<DropdownOption> found= new ArrayList<DropdownOption>();
		for (int i=0; i < test.size(); i++){
			
			String row[] = StringUtils.split(test.get(i), " ");
			found.add(new DropdownOption(Integer.parseInt(row[1]), row[0]));
			
		}
		model.addAttribute("found", found);
		Search search = new Search();
		search.setSearchBar(searchBar);
		model.addAttribute ("search", search);
		
		return "searchTrainingMaster";
		
	}
	
	@RequestMapping(value= "/search", params="select", method=RequestMethod.POST)
	public String showSearchPage(RedirectAttributes re, Search search,@RequestParam("result") int Result ){
		
		re.addAttribute("id", Result);
		return "redirect:/trainingMaster/edit";
		
	}
	
	
}
