package com.perficient.spring.web.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
	@Id
	private int employeeID;
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate endDate;
	
	private int employeeType;
	private int employeeDept;
	private int userRole;
	private String password;
	
	public Employee() {
		super();
	}
	
	public Employee(int employeeID, String firstName, String lastName,
			String phoneNumber, String emailAddress, LocalDate startDate, LocalDate endDate,
			int employeeType, int employeeDept, int userRole,
			String password) {
			super();
			this.employeeID = employeeID;
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.emailAddress = emailAddress;
			this.startDate = startDate;
			this.endDate = endDate;
			this.employeeType = employeeType;
			this.employeeDept = employeeDept;
			this.userRole = userRole;
			this.password = password;
	}
	
	public Employee(String firstName, String lastName,
			String phoneNumber, String emailAddress, LocalDate startDate, LocalDate endDate,
			int employeeType, int employeeDept, int userRole,
			String password) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.emailAddress = emailAddress;
			this.startDate = startDate;
			this.endDate = endDate;
			this.employeeType = employeeType;
			this.employeeDept = employeeDept;
			this.userRole = userRole;
			this.password = password;
	}
	
	

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}

	public int getEmployeeDept() {
		return employeeDept;
	}

	public void setEmployeeDept(int employeeDept) {
		this.employeeDept = employeeDept;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
