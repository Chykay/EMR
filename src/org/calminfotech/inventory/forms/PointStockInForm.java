package org.calminfotech.inventory.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class PointStockInForm {

	private int id;

	@Size(min = 0, max = 100, message = "Field must be less than 100 characters!")
	private String batchId;

	@NotBlank(message = "Field cannot be empty!")
	@Size(min = 1, max = 15, message = "Date Of Supply must between 1 and 15 characters!")
	private String stockInDate;

	private Integer unitId;

	private String description;

	private String manufacturedate;

	private String expirydate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String tinno;
	private String batchno;

	private String serialno;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getStockInDate() {
		return stockInDate;
	}

	public void setStockInDate(String stockInDate) {
		this.stockInDate = stockInDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

}
