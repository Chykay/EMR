package org.calminfotech.patient.forms;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Range;

public class PatientSocialHistoryForm {
	
	private Integer id;
	
	private Integer patientId;
	
	private Integer VisitId;
	
	private String education_level;
	
	private String employment_status;
	
	private String disabled;
	
	private String sexual_orientation;
	
	private String substance;
	
	private String currentorprevious;
	
	private Integer frequency;
	
	private String cycle;
	
	private Integer start_year;
	
	private Integer stop_year;
	
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

	public String getEducation_level() {
		return education_level;
	}

	public void setEducation_level(String education_level) {
		this.education_level = education_level;
	}

	public String getEmployment_status() {
		return employment_status;
	}

	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getSexual_orientation() {
		return sexual_orientation;
	}

	public void setSexual_orientation(String sexual_orientation) {
		this.sexual_orientation = sexual_orientation;
	}

	public String getSubstance() {
		return substance;
	}

	public void setSubstance(String substance) {
		this.substance = substance;
	}

	public String getCurrentorprevious() {
		return currentorprevious;
	}

	public void setCurrentorprevious(String currentorprevious) {
		this.currentorprevious = currentorprevious;
	}



	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public Integer getStart_year() {
		return start_year;
	}

	public void setStart_year(Integer start_year) {
		this.start_year = start_year;
	}

	public Integer getStop_year() {
		return stop_year;
	}

	public void setStop_year(Integer stop_year) {
		this.stop_year = stop_year;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Integer getVisitId() {
		return VisitId;
	}

	public void setVisitId(Integer visitId) {
		VisitId = visitId;
	}

	
	
	

}
