package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class PatientAllergyForm {
	
	private int id;

	@Range(min = 1, message = "No patient associated")
	private Integer patientId;


	@Range(min = 1, message = "Select an allergy")
	private Integer allergyId;
	
	

	
	@NotEmpty(message = "Cannot be empty!")
	private String reaction;

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}
	

}
