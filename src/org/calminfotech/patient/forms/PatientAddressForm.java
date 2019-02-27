package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Range;

public class PatientAddressForm {
	private Integer id;
	
	@Range(min = 1, message = "select a patient")
	
	private Integer patientId;

	private Integer addresstypeId;

	private String address;

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


	public Integer getAddresstypeId() {
		return addresstypeId;
	}

	public void setAddresstypeId(Integer addresstypeId) {
		this.addresstypeId = addresstypeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

}
