/**
 * @author justin.grothe
 *
 */
package com.perficient.spring.web.repository;

import java.util.ArrayList;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perficient.spring.web.model.Employee;

@Repository
public interface EmployeeRepository {
//public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	/**
	 * @author Justin Grothe
	 * returns Employee with given id
	 */
	Employee findOne(int id);
	
	/**
	 * @author Justin Grothe
	 * saves Employee to database, returns Employee
	 */
	Employee saveEmployee(Employee e);
	
	/**
	 * @author Justin Grothe
	 * adds Employee to database, returns id
	 */
	int addEmployee(Employee e);
	
	/**
	 * @author Justin Grothe
	 * finds matching from search terms from search application, returns like "first_name last_name id"
	 */
	ArrayList<String> findAll(String a);
	
	/**
	 * @author Justin Grothe
	 * changes password in database, returns id
	 */
	int changePassword(String password, String newpassword, int i);
}