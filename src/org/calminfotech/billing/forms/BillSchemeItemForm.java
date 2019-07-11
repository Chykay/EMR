package org.calminfotech.billing.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


public class BillSchemeItemForm {
	

	private Integer billSchemeItemId;
	private Integer billId;
	private String billName;
//	private String unitOfMeasurement;
	private Integer globalItemId;
	private Integer organisationId;
	
	
	
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	public Integer getBillSchemeItemId() {
		return billSchemeItemId;
	}
	public void setBillSchemeItemId(Integer billSchemeItemId) {
		this.billSchemeItemId = billSchemeItemId;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public Integer getGlobalItemId() {
		return globalItemId;
	}
	public void setGlobalItemId(Integer globalItemId) {
		this.globalItemId = globalItemId;
	}
	
	

	

}
