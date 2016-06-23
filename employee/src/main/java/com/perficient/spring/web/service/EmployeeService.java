package com.perficient.spring.web.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.perficient.spring.web.model.Employee;

@Service("employee-service")
public class EmployeeService {

	public Employee getSampleEmployee() {
		LocalDate startDate = LocalDate.parse("2015-08-17");
		LocalDate endDate = LocalDate.parse("2015-08-18");
		
		return new Employee("Justin", "Grothe", "248-760-9922",
				"justin.grothe@perficient.com", startDate, endDate,
				0, 1, 1, "1234");
	}
	
}
