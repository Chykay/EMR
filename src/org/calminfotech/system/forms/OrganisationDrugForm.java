package org.calminfotech.system.forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

public class OrganisationDrugForm {

	private int id;
	
	@Range(min=1, message = "Select a drug")
	private int drugId;
	
	@Pattern(regexp = "[0-9]*\\.[0-9]+|[0-9]+", message = "Insert a valid price! Eg: 1000 or 1500.50")
	private String price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
