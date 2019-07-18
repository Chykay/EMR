package org.calminfotech.inventory.forms;

import java.util.Collection;

public class RequestForm {

	private Integer id;
	// private String dateOfRequest;

	private String globalItem1;
	private String unitOfMeasure1;
	private String qty1;

	private String globalItem2;
	private String unitOfMeasure2;
	private String qty2;

	private String globalItem3;
	private String unitOfMeasure3;
	private String qty3;

	private Integer unitId;

	/*
	 * public String getDateOfRequest() { return dateOfRequest; }
	 * 
	 * public void setDateOfRequest(String dateOfRequest) { this.dateOfRequest =
	 * dateOfRequest; }
	 */
	// private Set<Integer> validOrders;
	private Collection optionalOrders;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGlobalItem1() {
		return globalItem1;
	}

	public void setGlobalItem1(String globalItem1) {
		this.globalItem1 = globalItem1;
	}

	public String getUnitOfMeasure1() {
		return unitOfMeasure1;
	}

	public void setUnitOfMeasure1(String unitOfMeasure1) {
		this.unitOfMeasure1 = unitOfMeasure1;
	}

	public String getQty1() {
		return qty1;
	}

	public void setQty1(String qty1) {
		this.qty1 = qty1;
	}

	public String getGlobalItem2() {
		return globalItem2;
	}

	public void setGlobalItem2(String globalItem2) {
		this.globalItem2 = globalItem2;
	}

	public String getUnitOfMeasure2() {
		return unitOfMeasure2;
	}

	public void setUnitOfMeasure2(String unitOfMeasure2) {
		this.unitOfMeasure2 = unitOfMeasure2;
	}

	public String getQty2() {
		return qty2;
	}

	public void setQty2(String qty2) {
		this.qty2 = qty2;
	}

	public String getGlobalItem3() {
		return globalItem3;
	}

	public void setGlobalItem3(String globalItem3) {
		this.globalItem3 = globalItem3;
	}

	public String getUnitOfMeasure3() {
		return unitOfMeasure3;
	}

	public void setUnitOfMeasure3(String unitOfMeasure3) {
		this.unitOfMeasure3 = unitOfMeasure3;
	}

	public String getQty3() {
		return qty3;
	}

	public void setQty3(String qty3) {
		this.qty3 = qty3;
	}

	public Collection getOptionalOrders() {
		return optionalOrders;
	}

	public void setOptionalOrders(Collection optionalOrders) {
		this.optionalOrders = optionalOrders;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

}
