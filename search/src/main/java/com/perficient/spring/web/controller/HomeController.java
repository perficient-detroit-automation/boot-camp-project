package com.perficient.spring.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
				String randomUrl = "http://localhost:8080/";
				URI targetUrl = UriComponentsBuilder.fromHttpUrl(randomUrl).path("/search").build().toUri();	
				ArrayList<String> test = new ArrayList<String>();
				test =  new RestTemplate().postForObject(targetUrl, searchBar, ArrayList.class);
				System.out.println("results");
				//System.out.println("results: " + test.get(0));
			} else { // who = employee

			}
		}
		System.out.println("Execute button pressed");
		return "search";
	}
	
	
}
