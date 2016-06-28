package com.perficient.spring.web.model;


import java.sql.Blob;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Nick Umble - Original author.
 */
@Entity
@Table(name = "CANDIDATE")
public final class Candidate {

	@Id
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
		//this.resume = resume;
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
	//	this.resume = resume;
	}

  @Column(name = "PERSON_ID")
	public int getPersonID() {
		return personID;
	}

  @Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

  @Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}
  @Column(name = "PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}

  @Column(name = "EMAIL_ADDRESS")
	public String getEmailAddress() {
		return emailAddress;
	}

  @Column(name = "START_DATE")
	public LocalDate getStartDate() {
		return startDate;
	}

  @Column(name = "DEGREE")
  public int getDegree() {
		return degree;
	}

  @Column(name = "MAJOR")
	public String getMajor() {
		return major;
	}

  @Column(name = "SKILL_SET")
	public String getSkillset() {
		return skillset;
	}

  @Column(name = "GRADUATION_DATE")
	public LocalDate getGraduationDate() {
		return graduationDate;
	}

  @Column(name = "STATUS")
  public int getStatus() {
		return status;
	}

  @Column(name = "COMMENTS")
	public String getComments() {
		return comments;
	}

  @Column(name = "RESUME")
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
