package com.perficient.spring.web.model;

/**
 * <p>Retains data for use within a &lt;select> tag in Thymeleaf as an individual &lt;option> tag.</p>
 * 
 * @author Nick Umble - Original author.
 */
public class DropdownOption {

	private int id;
	private String description;


	public DropdownOption(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	

	public DropdownOption() {
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
		return "DropdownOption [id=" + id + ", description=" + description + "]";
	}


}
