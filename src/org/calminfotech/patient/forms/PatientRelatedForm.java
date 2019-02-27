package org.calminfotech.patient.forms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.calminfotech.patient.models.Patient;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class PatientRelatedForm {
	
private Integer id;
	
private int patientId;

private int relPatientId;

@NotBlank(message = "Field cannot be empty!")
private Integer relationshiptypeId;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public int getPatientId() {
	return patientId;
}

public void setPatientId(int patientId) {
	this.patientId = patientId;
}

public int getRelPatientId() {
	return relPatientId;
}

public Integer getRelationshiptypeId() {
	return relationshiptypeId;
}

public void setRelationshiptypeId(Integer relationshiptypeId) {
	this.relationshiptypeId = relationshiptypeId;
}

public void setRelPatientId(int relPatientId) {
	System.out.println("relatedid"+relPatientId);
	this.relPatientId = relPatientId;
}



}
