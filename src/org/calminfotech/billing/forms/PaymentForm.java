package org.calminfotech.billing.forms;

/**
 * @author adeoye
 * 
 */
public class PaymentForm {

	private Integer id;

	private String effectivedate;

	private Double amtpaid;

	private String description;

	private Integer paymode_id;

	private Integer visitId;

	private Integer visitinvId;

	private Integer billinvoiceId;

	public Integer getBillinvoiceId() {
		return billinvoiceId;
	}

	public void setBillinvoiceId(Integer billinvoiceId) {
		this.billinvoiceId = billinvoiceId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVisitinvId() {
		return visitinvId;
	}

	public void setVisitinvId(Integer visitinvId) {
		this.visitinvId = visitinvId;
	}

	public String getEffectivedate() {
		return effectivedate;
	}

	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}

	public Double getAmtpaid() {
		return amtpaid;
	}

	public void setAmtpaid(Double amtpaid) {
		this.amtpaid = amtpaid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPaymode_id() {
		return paymode_id;
	}

	public void setPaymode_id(Integer paymode_id) {
		this.paymode_id = paymode_id;
	}

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

}
