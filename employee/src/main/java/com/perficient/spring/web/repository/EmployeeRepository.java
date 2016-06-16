package com.perficient.spring.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perficient.spring.web.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Employee findOne(int id);
	Employee saveEmployee(Employee c);
	Employee addEmployee(Employee c);
}