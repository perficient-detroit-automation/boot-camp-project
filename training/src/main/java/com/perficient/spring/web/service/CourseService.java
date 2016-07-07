package com.perficient.spring.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.spring.web.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	public ArrayList<String> findAll(String searchBar){
		//ArrayList<String> courseTitles = new ArrayList<String>();
		ArrayList<String> courses = courseRepository.findAll(searchBar);
		System.out.println(courses.get(0));
		
		return courses;
//		for(int i= 0; i<courses.size(); i++){
//			courseTitles.add(courses.get(i).getCourseTitle());
//		}
//				
//	 return courseTitles;
	}
	
}
