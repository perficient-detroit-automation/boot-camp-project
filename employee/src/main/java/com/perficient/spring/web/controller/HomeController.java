package com.perficient.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perficient.spring.web.model.DropdownOption;
import com.perficient.spring.web.model.Employee;

@Controller
public class HomeController {
	
	@ModelAttribute("employeeType")
	public List<DropdownOption> populateWhichType() {
		List<DropdownOption> employeeType = new ArrayList<DropdownOption>();

		employeeType.add(new DropdownOption(0, "Intern"));
		employeeType.add(new DropdownOption(1, "Part-Time"));
		employeeType.add(new DropdownOption(2, "Full-Time"));

		return employeeType;
	}
	
	@ModelAttribute("employeeDept")
	public List<DropdownOption> populateDept() {
		List<DropdownOption> employeeType = new ArrayList<DropdownOption>();

		employeeType.add(new DropdownOption(0, "Automation"));
		employeeType.add(new DropdownOption(1, "Development"));
		employeeType.add(new DropdownOption(2, "Testing"));

		return employeeType;
	}
	
	@ModelAttribute("userRole")
	public List<DropdownOption> populateRoles() {
		List<DropdownOption> employeeType = new ArrayList<DropdownOption>();

		employeeType.add(new DropdownOption(0, "Candidate"));
		employeeType.add(new DropdownOption(1, "Employee"));
		employeeType.add(new DropdownOption(2, "Administrator"));

		return employeeType;
	}

	@RequestMapping(value = "/")
	public String showThymeleafPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "manage-personnel_EmpThyme";
	}
	
}
