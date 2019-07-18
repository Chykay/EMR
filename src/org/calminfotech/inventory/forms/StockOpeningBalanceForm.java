package org.calminfotech.inventory.forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class StockOpeningBalanceForm {

	private int id;

	@NotBlank(message = "Field cannot be empty!")
	private String globalItem;

	@NotBlank(message = "Field cannot be empty!")
	@Size(min = 1, max = 15, message = "Date must between 1 and 15 characters!")
	private String dateAdded;

	@NotBlank(message = "Field cannot be empty!")
	private String unitOfMeasure;

	@Digits(fraction = 0, integer = 15, message = "Opening Balance must be a number and between 1 and 15 characters!")
	private String openingBalance;

	/*
	 * @NotBlank(message = "Field cannot be empty!") private String
	 * containedUnit;
	 */

	private String reorderlevel;

	private Double cost;

	private String action;
	private String tinno;

	private String serialno;
	private String description;

	public String getManulicno() {
		return manulicno;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getTinno() {
		return tinno;
	}

	public void setTinno(String tinno) {
		this.tinno = tinno;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setManulicno(String manulicno) {
		this.manulicno = manulicno;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	private String manulicno;
	private String batchno;

	private String manufacturedate;

	private String expirydate;

	public int getId() {
		return id;
	}

	public String getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(String globalItem) {
		this.globalItem = globalItem;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}

	public String getManufacturedate() {
		return manufacturedate;
	}

	public void setManufacturedate(String manufacturedate) {
		this.manufacturedate = manufacturedate;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public String getReorderlevel() {
		return reorderlevel;
	}

	public void setReorderlevel(String reorderlevel) {
		this.reorderlevel = reorderlevel;
	}

	/*
	 * public String getOpeningBalance() { return openingBalance; }
	 * 
	 * public void setOpeningBalance(String openingBalance) {
	 * this.openingBalance = openingBalance; }
	 */

}
