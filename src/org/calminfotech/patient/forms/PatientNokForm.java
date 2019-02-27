package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Range;

public class PatientNokForm {
	private Integer id;
	
	@Range(min = 1, message = "select a patient")
	
	private Integer patientId;

	private Integer relationshiptypeId;

	public Integer getRelationshiptypeId() {
		return relationshiptypeId;
	}

	public void setRelationshiptypeId(Integer relationshiptypeId) {
		this.relationshiptypeId = relationshiptypeId;
	}

	private String fullname;
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String email;
	
	private String telephone;
	
	private  String address;
	

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




	

}
