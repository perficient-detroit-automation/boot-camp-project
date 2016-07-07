package com.perficient.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.perficient.spring.web.model.DropdownOption;
import com.perficient.spring.web.model.Search;
import com.perficient.spring.web.service.CourseService;

@Controller
@RequestMapping(value="/course")
public class CourseController {
	
	@Autowired
	private CourseService service;
	
	@RequestMapping(value="")
	public String goToSearch() {
		return "redirect:course/search";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String showHome(Model model){
		model.addAttribute("search", new Search());
		return "searchCourse";
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
		
		return "search";
		
	}

}
