package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Email;


public class CasualtyPatientForm {
	
	
	private Integer id;
	
	private String patient_id;

	private String surname;

	private String othernames;

	@Email(message = "Invalid email format")
	private String email;

	private String homeNumber;
	
	private String genotype;
	
	private String bldgrp;
	
	private String height;
	

	private String phoneNumber;

	private String dob;

	private String address;
	
	
	private Integer lgaId;

	
	private Integer stateId;
	
	
	private Integer statusId;
	
	
	private Integer titleId;

	
	private Integer languageId;

	
	private Integer genderId;


	public Integer getId() {
		return id;
	}


	public void setId(Integer string) {
		this.id = string;
	}


	public String getPatient_id() {
		return patient_id;
	}


	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getOthernames() {
		return othernames;
	}


	public void setOthernames(String othernames) {
		this.othernames = othernames;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getHomeNumber() {
		return homeNumber;
	}


	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}


	public String getGenotype() {
		return genotype;
	}


	public void setGenotype(String genotype) {
		this.genotype = genotype;
	}


	public String getBldgrp() {
		return bldgrp;
	}


	public void setBldgrp(String bldgrp) {
		this.bldgrp = bldgrp;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Integer getLgaId() {
		return lgaId;
	}


	public void setLgaId(Integer lgaId) {
		this.lgaId = lgaId;
	}


	public Integer getStateId() {
		return stateId;
	}


	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}


	public Integer getStatusId() {
		return statusId;
	}


	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}


	public Integer getTitleId() {
		return titleId;
	}


	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}


	public Integer getLanguageId() {
		return languageId;
	}


	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}


	public Integer getGenderId() {
		return genderId;
	}


	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	
	

}
