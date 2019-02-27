package org.calminfotech.patient.forms;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Range;

public class PatientEmergencyForm {

	
	private Integer id;
	
	private Integer patientId;
	
	@Range(min = 1, message = "Select a title")
	private Integer titleId;
	
	private String full_name;
	
	private String relationship;

	private String email_id;
	

	private String home_address;
	

	private String mobile_no1;
	

	private String mobile_no2;
	

	private String other_detail;


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


	public String getFull_name() {
		return full_name;
	}


	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}


	public String getEmail_id() {
		return email_id;
	}


	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}


	public String getHome_address() {
		return home_address;
	}


	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}


	public String getMobile_no1() {
		return mobile_no1;
	}


	public void setMobile_no1(String mobile_no1) {
		this.mobile_no1 = mobile_no1;
	}


	public String getMobile_no2() {
		return mobile_no2;
	}


	public void setMobile_no2(String mobile_no2) {
		this.mobile_no2 = mobile_no2;
	}


	public String getOther_detail() {
		return other_detail;
	}


	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}


	public String getRelationship() {
		return relationship;
	}


	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}


	public Integer getTitleId() {
		return titleId;
	}


	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}
	
	
	
	
}
