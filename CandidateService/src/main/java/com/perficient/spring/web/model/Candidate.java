package com.perficient.spring.web.model;


import java.sql.Blob;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


public final class Candidate {

	private int personID;

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate startDate;
	private int degree;
	private String major;
	private String skillset;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private LocalDate graduationDate;
	private int status;
	private String comments;
	private Blob resume;


	public Candidate() {
		super();
	}

	public Candidate(int personID, String firstName, String lastName,
		String phoneNumber, String emailAddress, LocalDate startDate, int degree,
		String major, String skillset, LocalDate graduationDate, int status,
		String comments, Blob resume) {
		super();
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.startDate = startDate;
		this.degree = degree;
		this.major = major;
		this.skillset = skillset;
		this.graduationDate = graduationDate;
		this.status = status;
		this.comments = comments;
		this.resume = resume;
	}

	public Candidate(String firstName, String lastName, String phoneNumber,
		String emailAddress, LocalDate startDate, int degree, String major,
		String skillset, LocalDate graduationDate, int status, String comments,
		Blob resume) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.startDate = startDate;
		this.degree = degree;
		this.major = major;
		this.skillset = skillset;
		this.graduationDate = graduationDate;
		this.status = status;
		this.comments = comments;
		this.resume = resume;
	}


	public int getPersonID() {
		return personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public int getDegree() {
		return degree;
	}

	public String getMajor() {
		return major;
	}

	public String getSkillset() {
		return skillset;
	}

	public LocalDate getGraduationDate() {
		return graduationDate;
	}

	public int getStatus() {
		return status;
	}

	public String getComments() {
		return comments;
	}

	public Blob getResume() {
		return resume;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}

	public void setGraduationDate(LocalDate graduationDate) {
		this.graduationDate = graduationDate;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setResume(Blob resume) {
		this.resume = resume;
	}

}
