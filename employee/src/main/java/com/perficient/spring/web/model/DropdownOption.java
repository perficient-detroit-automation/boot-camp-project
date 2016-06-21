package com.perficient.spring.web.model;


public class DropdownOption {

	private int id;
	private String description;


	public DropdownOption(int id, String description) {
		super();
		this.id = id;
		this.description = description;
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
