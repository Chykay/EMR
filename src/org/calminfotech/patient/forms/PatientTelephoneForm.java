package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Range;

public class PatientTelephoneForm {
	private Integer id;
	
	@Range(min = 1, message = "select a patient")
	
	private Integer patientId;

	private Integer phonetypeId;

	private String telnumber;

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

	public Integer getPhonetypeId() {
		return phonetypeId;
	}

	public void setPhonetypeId(Integer phonetypeId) {
		this.phonetypeId = phonetypeId;
	}

	public String getTelnumber() {
		return telnumber;
	}

	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}


}
