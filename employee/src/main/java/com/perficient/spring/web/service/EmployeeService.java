/**
 * @author justin.grothe
 *
 */
package com.perficient.spring.web.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.spring.web.model.Employee;
import com.perficient.spring.web.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee getSampleEmployee() {
		LocalDate startDate = LocalDate.parse("2015-08-17");
		LocalDate endDate = LocalDate.parse("2015-08-18");
		
		return new Employee("Justin", "Grothe", "248-760-9922",
				"justin.grothe@perficient.com", startDate, endDate,
				0, 1, 1, "1234");
	}
	
	public int addEmployee(Employee e) {
		return employeeRepository.addEmployee(e);
	}
	
	public Employee findOne(int id) {
		return employeeRepository.findOne(id);
	}
	
	public ArrayList<String> findAll(String params) {
		return employeeRepository.findAll(params);
	}

	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.saveEmployee(employee);
	}

	public int changePassword(String password, String newpassword, int i) {
		return employeeRepository.changePassword(password, newpassword, i);
	}
	
}
