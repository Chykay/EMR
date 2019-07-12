package org.calminfotech.billing.forms;

public class BillSchemeItemPriceForm {
	
	private Integer billItemPriceId;
	private Integer billSchemeItemId;
	private Integer globalItemId;
	private Integer unitOfMeasure; 
	private Double billItemPrice;
	private Integer organisationId;
	
	public Integer getBillItemPriceId() {
		return billItemPriceId;
	}
	public void setBillItemPriceId(Integer billItemPriceId) {
		this.billItemPriceId = billItemPriceId;
	}
	public Integer getBillSchemeItemId() {
		return billSchemeItemId;
	}
	public void setBillSchemeItemId(Integer billSchemeItemId) {
		this.billSchemeItemId = billSchemeItemId;
	}
	public Integer getGlobalItemId() {
		return globalItemId;
	}
	public void setGlobalItemId(Integer globalItemId) {
		this.globalItemId = globalItemId;
	}
	
	
	public Integer getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(Integer unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public Double getBillItemPrice() {
		return billItemPrice;
	}
	public void setBillItemPrice(Double billItemPrice) {
		this.billItemPrice = billItemPrice;
	}
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	


}
