package org.calminfotech.patient.forms;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.hibernate.validator.constraints.Range;

public class PatientPaymentOptionForm {
	

	private Integer id;
	
	private Integer patientId;
	
	@Range(min = 1, message = "Select a payment scheme")
	private Integer paymentSchemeId; 
	
	@Range(min = 1, message = "Select a payment scheme item")
	private Integer paymentSchemeitemId; 
		
	private String comment;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getPaymentSchemeId() {
		return paymentSchemeId;
	}

	public void setPaymentSchemeId(Integer paymentSchemeId) {
		this.paymentSchemeId = paymentSchemeId;
	}

	public Integer getPaymentSchemeitemId() {
		return paymentSchemeitemId;
	}

	public void setPaymentSchemeitemId(Integer paymentSchemeitemId) {
		this.paymentSchemeitemId = paymentSchemeitemId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	

}
