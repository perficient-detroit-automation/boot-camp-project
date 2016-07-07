package com.perficient.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/course")
public class CourseController {
	
	@RequestMapping(value="/test")
	public String showHome(){
	
		return "Course";
	}

}
