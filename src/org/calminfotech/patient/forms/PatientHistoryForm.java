package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Range;

public class PatientHistoryForm {
	private Integer id;
	
	@Range(min = 1, message = "select a patient")
	
	private Integer patientId;

	private Integer historytypeId;

	private String history;

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

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	

}
