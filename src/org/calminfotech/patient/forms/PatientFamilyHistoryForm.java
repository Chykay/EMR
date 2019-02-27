package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Range;

public class PatientFamilyHistoryForm {
	private Integer id;
	
	@Range(min = 1, message = "select a patient")
	
	private Integer patientId;

	private Integer historytypeId;

	private String familyhistory;

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


	public Integer getHistorytypeId() {
		return historytypeId;
	}

	public void setHistorytypeId(Integer historytypeId) {
		this.historytypeId = historytypeId;
	}

	public String getFamilyhistory() {
		return familyhistory;
	}

	public void setFamilyhistory(String familyhistory) {
		this.familyhistory = familyhistory;
	}


	

}
