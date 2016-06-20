package com.perficient.spring.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import com.perficient.spring.web.model.DropdownOption;

@Controller
@SessionAttributes({"action", "who", "SearchBar"})
public class HomeController {
	
	@ModelAttribute("addOrFind")
	public List<DropdownOption> populateAddOrFind() {
		List<DropdownOption> addOrFind = new ArrayList<DropdownOption>();

		addOrFind.add(new DropdownOption(0, "Add new"));
		addOrFind.add(new DropdownOption(1, "Find current"));

		return addOrFind;
	}
	
	@ModelAttribute("whichType")
	public List<DropdownOption> populateWhichType() {
		List<DropdownOption> whichType = new ArrayList<DropdownOption>();

		whichType.add(new DropdownOption(0, "Candidate"));
		whichType.add(new DropdownOption(1, "Employee"));

		return whichType;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage() {
		return "search";
	}
	
	
	@RequestMapping(value = "/", params = "execute", method = RequestMethod.POST)
	public String addButton(@RequestParam("SearchBar") String searchBar, @RequestParam("action") int action, @RequestParam("who") int who, Model model) {
		System.out.println("Action: " + action + " Who: " + who);
		if (action == 0) { // Want to add either candidate or employee
			if (who == 0) {
				return "redirect:http://localhost:8080/add";
			} else { // who = employee
				// Once employee application is working, can uncomment below line. Need to change employee service to port 8082
				//return "redirect:http://localhost:8082/add";
			}
		} else  if (action == 1){ // Want to find candidate or employee
			List<DropdownOption> found = new ArrayList<DropdownOption>();
			if (who == 0) {
				String randomUrl = "http://localhost:8080/";
				URI targetUrl = UriComponentsBuilder.fromHttpUrl(randomUrl).path("/search").build().toUri();	
				ArrayList<String> test = new ArrayList<String>();
				test =  new RestTemplate().postForObject(targetUrl, searchBar, ArrayList.class);
				if (test == null) {
					System.out.println("Return from candidate database is null");
				} else {
					System.out.println("results: ");
					for (int i = 0; i < test.size(); i++) {
						String row[] = StringUtils.split(test.get(i), " ");
						found.add(new DropdownOption(Integer.parseInt(row[2]), row[0] + " " + row[1]));
					}
				}
				model.addAttribute("found", found);
			} else { // who = employee

			}
		}
		System.out.println("Execute button pressed");
		model.addAttribute("action", action);
		model.addAttribute("who", who);
		return "search";
	}
	
	@RequestMapping(value="/", params="select", method=RequestMethod.POST)
	public String selectButton(@RequestParam("SearchBar") String searchBar, @RequestParam("action") String action, @RequestParam("who") String who, @RequestParam("ResultsDropdown") String resultsDropdown) {
		System.out.println("Select button pressed");
		return "search";
	}
	
	
}
