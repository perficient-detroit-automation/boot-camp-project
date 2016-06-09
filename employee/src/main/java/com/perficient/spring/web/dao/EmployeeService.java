package com.perficient.spring.web.dao;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service("employee-service")
public class EmployeeService {
	
	//@Autowired
	//private EmployeeDAO dao;

	public Employee getSampleEmployee() {
		LocalDate startDate = LocalDate.parse("2015-08-17");
		LocalDate endDate = LocalDate.parse(null);
		
		return new Employee("Justin", "Grothe", "248-760-9922",
				"justin.grothe@perficient.com", startDate, endDate,
				0, 1, 1, "1234");
	}
	
}
