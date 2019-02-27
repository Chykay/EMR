package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;

public class ConfigBillMForm {

	@NotBlank(message = "Field cannot be empty!")
	private Integer patientId;

	@NotBlank(message = "Field cannot be empty!")
	private Integer visitId;
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer sectionId;
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer billingId;
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer currentPiointId;
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer item;
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer itemType;
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer unitofmeasure;
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer price;
	
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getBillingId() {
		return billingId;
	}

	public void setBillingId(Integer billingId) {
		this.billingId = billingId;
	}

	public Integer getCurrentPiointId() {
		return currentPiointId;
	}

	public void setCurrentPiointId(Integer currentPiointId) {
		this.currentPiointId = currentPiointId;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Integer getUnitofmeasure() {
		return unitofmeasure;
	}

	public void setUnitofmeasure(Integer unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
