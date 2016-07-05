package com.perficient.spring.web.model;

public class TrainingMaster {

	private int trainingID;
	private String title;
	private String description;
	private int duration;
	private int durationUnit;
	private String URL;
	private int trainingStatus;
	private int bootcampType;
	
	public TrainingMaster(){
		
	}
	
	public TrainingMaster(String title, String description, int duration, int durationUnit, String URL, int trainingStatus, int bootcampType){
		this.title=title;
		this.description=description;
		this.duration=duration;
		this.durationUnit=durationUnit;
		this.URL=URL;
		this.trainingStatus=trainingStatus;
		this.bootcampType=bootcampType;
	}
	
	public int getTrainingID() {
		return trainingID;
	}

	public void setTrainingID(int trainingID) {
		this.trainingID = trainingID;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDurationUnit() {
		return durationUnit;
	}
	public void setDurationUnit(int durationUnit) {
		this.durationUnit = durationUnit;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public int getTrainingStatus() {
		return trainingStatus;
	}
	public void setTrainingStatus(int trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
	public int getBootcampType() {
		return bootcampType;
	}
	public void setBootcampType(int bootcampType) {
		this.bootcampType = bootcampType;
	}
}