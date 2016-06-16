package com.perficient.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage() {
		return "search";
	}
	
	@RequestMapping(value = "/", params = "execute", method = RequestMethod.POST)
	public String addButton(@RequestParam("SearchBar") String searchBar, @RequestParam("action") String action, @RequestParam("who") String who) {
		System.out.println("Action: " + action + " Who: " + who);
		if (action.equals("add")) { // Want to add either candidate or employee
			if (who.equals("candidate")) {
				return "redirect:http://localhost:8080/add";
			} else { // who = employee
				// Once employee application is working, can uncomment below line. Need to change employee service to port 8082
				//return "redirect:http://localhost:8082/add";
			}
		} else  if (action.equals("find")){ // Want to find candidate or employee
			if (who.equals("candidate")) {

			} else { // who = employee

			}
		}
		System.out.println("Execute button pressed");
		return "search";
	}
	
	
}
