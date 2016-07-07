package com.perficient.spring.web.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perficient.spring.web.model.Course;

@Repository
public interface CourseRepository extends JpaRepository <Course, Integer> {

	ArrayList<String> findAll(String searchKeyWord);
	
}
