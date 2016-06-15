package com.perficient.spring.web.model;


public class EnumTableRow {

	private int id;
	private String description;


	public EnumTableRow(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	

	public EnumTableRow() {
		//super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "EnumTableRow [id=" + id + ", description=" + description + "]";
	}


}
