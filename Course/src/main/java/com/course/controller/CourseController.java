package com.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {
	
	@RequestMapping(value="/")
	public String showHome(){
	
		return "Test";
	}

}
