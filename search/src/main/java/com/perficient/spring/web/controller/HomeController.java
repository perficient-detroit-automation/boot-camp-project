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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import com.perficient.spring.web.model.DropdownOption;
import com.perficient.spring.web.model.Search;

@Controller
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
	public String showHomePage(Model model) {
		model.addAttribute("search", new Search());
		return "search";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", params = "execute", method = RequestMethod.POST)
	public String addButton(@RequestParam("searchBar") String searchBar, @RequestParam("action") int action, @RequestParam("whichType") int whichType, Model model) {
		if (action == 0) { // Want to add either candidate or employee
			if (whichType == 0) {
				return "redirect:http://localhost:8080/add";
			} else { // whichType = employee
				return "redirect:http://localhost:8082/add";
			}
		} else  if (action == 1){ // Want to find candidate or employee
			List<DropdownOption> found = new ArrayList<DropdownOption>();
			if (whichType == 0) {
				String randomUrl = "http://localhost:8080/";
				URI targetUrl = UriComponentsBuilder.fromHttpUrl(randomUrl).path("/search").build().toUri();	
				ArrayList<String> test = new ArrayList<String>();
				test =  new RestTemplate().postForObject(targetUrl, searchBar, ArrayList.class);
				if (test == null) {
					System.out.println("Return from candidate database is null");
				} else {
					for (int i = 0; i < test.size(); i++) {
						String row[] = StringUtils.split(test.get(i), " ");
						found.add(new DropdownOption(Integer.parseInt(row[2]), row[0] + " " + row[1]));
					}
				}
				model.addAttribute("found", found);
			} else { // whichType = 1 (employee)
				String randomUrl = "http://localhost:8082/";
				URI targetUrl = UriComponentsBuilder.fromHttpUrl(randomUrl).path("/search").build().toUri();	
				ArrayList<String> test = new ArrayList<String>();
				test =  new RestTemplate().postForObject(targetUrl, searchBar, ArrayList.class);
				if (test == null) {
					System.out.println("Return from employee database is null");
				} else {
					System.out.println("Return from employee database not null");
					for (int i = 0; i < test.size(); i++) {
						String row[] = StringUtils.split(test.get(i), " ");
						found.add(new DropdownOption(Integer.parseInt(row[2]), row[0] + " " + row[1]));
					}
					model.addAttribute("found", found);
					System.out.println(found.get(0).getDescription());
					System.out.println(found.get(0).getId());
				}
			}
		}
		Search search = new Search();
		search.setAction(action);
		search.setWhichType(whichType);
		search.setSearchBar(searchBar);
		model.addAttribute("search", search);
		return "search";
	}
	
	@RequestMapping(value="/", params="select", method=RequestMethod.POST)
	public String selectButton(@RequestParam("whichType") int whichType, @RequestParam("resultsDropdown") String resultsDropdown, RedirectAttributes re) {
		re.addAttribute("id", resultsDropdown);
		if (whichType == 0) { //Candidate
			return "redirect:http://localhost:8080/edit";
		} else { //Employee
			return "redirect:http://localhost:8082/edit";
		}
	}
	
	
}
