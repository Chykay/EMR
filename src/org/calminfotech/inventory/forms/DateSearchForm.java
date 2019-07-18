package org.calminfotech.inventory.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class DateSearchForm {
	
	private int id;
	private String searchCriteria;
		
	@NotBlank(message = "Field cannot be empty!")
    @Size(min = 1, max = 15, message = "Date of request must between 1 and 15 characters!")
	private String dateOfRequest;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public String getDateOfRequest() {
		return dateOfRequest;
	}

	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}


	
}
