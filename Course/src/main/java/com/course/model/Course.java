package com.course.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {

	@Id
	private int courseId;
	private  String courseTitle;
	private String courseDescription;
	private String courseUrl;
	
	public Course(int courseId, String courseTitle, String courseDescription, String courseUrl) {
		super();
		this.courseId = courseId;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.courseUrl = courseUrl;
	}

	@Column (name = "COURSE_ID")
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	@Column (name = "COURSE_TITLE")
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	@Column (name = "COURSE_DESCRIPTION")
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	@Column (name = "COURSE_URL")
	public String getCourseUrl() {
		return courseUrl;
	}
	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}
	
	
	
}
