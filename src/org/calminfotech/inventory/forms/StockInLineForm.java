package org.calminfotech.inventory.forms;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;

public class StockInLineForm {

	private int id;

	private int batchId;

	private String globalitemname;

	@NotBlank(message = "Field cannot be empty!")
	private String globalItem;

	private String description;

	@NotBlank(message = "Field cannot be empty!")
	private String unitOfMeasure;

	@Digits(fraction = 0, integer = 15, message = "Quantity must be a number and between 1 and 15 characters!")
	private String quantity;

	private String tinno;
	private String batchno;

	private String serialno;

	private String manufacturedate;

	private String expirydate;
	private Double cost;

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
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

	public String getGlobalitemname() {
		return globalitemname;
	}

	public void setGlobalitemname(String globalitemname) {
		this.globalitemname = globalitemname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(String globalItem) {
		this.globalItem = globalItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

}
