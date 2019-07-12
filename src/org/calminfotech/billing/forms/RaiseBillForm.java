package org.calminfotech.billing.forms;

public class RaiseBillForm {
	
	private int visitId;
	
	private int activityId;
	
	private int billSchemeId;

	private int globalItemId;
	
	private int unitOfMeasureId;
	
	private int price;
	
	private int amount;
	
	private int discount;
	
	private int quantity;
	
	private Boolean usehmo;
	
	private int total;

	public int getGlobalItemId() {
		return globalItemId;
	}

	public void setGlobalItemId(int globalItemId) {
		this.globalItemId = globalItemId;
	}

	public int getUnitOfMeasureId() {
		return unitOfMeasureId;
	}

	public void setUnitOfMeasureId(int unitOfMeasureId) {
		this.unitOfMeasureId = unitOfMeasureId;
	}

	/*public int getBillSchemeId() {
		return billSchemeId;
	}

	public void setBillSchemeId(int billSchemeId) {
		this.billSchemeId = billSchemeId;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}*/

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getBillSchemeId() {
		return billSchemeId;
	}

	public void setBillSchemeId(int billSchemeId) {
		this.billSchemeId = billSchemeId;
	}

	public Boolean getUsehmo() {
		return usehmo;
	}

	public void setUsehmo(Boolean usehmo) {
		this.usehmo = usehmo;
	}

	

}
