package com.perficient.spring.web.model;

/**
 * @author justin.grothe
 *
 */
public class Search {

	private int action;
	private int whichType;
	private String searchBar;
	private int resultsDropdown;
	
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getWhichType() {
		return whichType;
	}
	public void setWhichType(int whichType) {
		this.whichType = whichType;
	}
	public String getSearchBar() {
		return searchBar;
	}
	public void setSearchBar(String searchBar) {
		this.searchBar = searchBar;
	}
	public int getResultsDropdown() {
		return resultsDropdown;
	}
	public void setResultsDropdown(int resultsDropdown) {
		this.resultsDropdown = resultsDropdown;
	}
	
}
