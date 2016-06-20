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
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import com.perficient.spring.web.model.DropdownOption;

@Controller
public class HomeController {
	
	@ModelAttribute("statuses")
	public List<DropdownOption> populateStatuses() {
		List<DropdownOption> statuses = new ArrayList<DropdownOption>();

//		statuses.add(new DropdownOption(0, "Interviewing"));
//		statuses.add(new DropdownOption(1, "Waiting on response"));
//		statuses.add(new DropdownOption(2, "Discontinued pursuit"));
//		statuses.add(new DropdownOption(3, "Hired"));

		return statuses;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage() {
		return "search";
	}
	
	@RequestMapping(value = "/", params = "execute", method = RequestMethod.POST)
	public String addButton(@RequestParam("SearchBar") String searchBar, @RequestParam("action") String action, @RequestParam("who") String who, Model model) {
		System.out.println("Action: " + action + " Who: " + who);
		if (action.equals("add")) { // Want to add either candidate or employee
			if (who.equals("candidate")) {
				return "redirect:http://localhost:8080/add";
			} else { // who = employee
				// Once employee application is working, can uncomment below line. Need to change employee service to port 8082
				//return "redirect:http://localhost:8082/add";
			}
		} else  if (action.equals("find")){ // Want to find candidate or employee
			List<DropdownOption> found = new ArrayList<DropdownOption>();
			if (who.equals("candidate")) {
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
		return "search";
	}
	
	
}
