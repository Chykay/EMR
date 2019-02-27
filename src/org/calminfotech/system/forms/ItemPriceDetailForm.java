package org.calminfotech.system.forms;

import java.util.Date;


public class ItemPriceDetailForm {

	
	private Integer id;
	private Integer paymentSchemeItemId;
	private String price;
	private Integer quantity;
	private Integer unitofMeasureId;
	private String comment;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getPaymentSchemeItemId() {
		return paymentSchemeItemId;
	}
	public void setPaymentSchemeItemId(Integer paymentSchemeItemId) {
		this.paymentSchemeItemId = paymentSchemeItemId;
	}
	
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	public Integer getUnitofMeasureId() {
		return unitofMeasureId;
	}
	public void setUnitofMeasureId(Integer unitofMeasureId) {
		this.unitofMeasureId = unitofMeasureId;
	}
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

	
}
