package org.calminfotech.patient.forms;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



public class PatientMedicalHistoryForm {
	

	private Integer id;
	
	private Integer patientId;
	
	private Integer VisitId;
	
	private Integer ailment_id;
	
	private Integer drug_id;
		
	private String ailment_detail;

	private String other_drugs;
	
	private String other_detail;
	
	private String shade_newfield;
	
	
	
	
	public Integer getAilment_id() {
		return ailment_id;
	}

	public void setAilment_id(Integer ailment_id) {
		this.ailment_id = ailment_id;
	}

	public Integer getDrug_id() {
		return drug_id;
	}

	public void setDrug_id(Integer drug_id) {
		this.drug_id = drug_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAilment_detail() {
		return ailment_detail;
	}

	public void setAilment_detail(String ailment_detail) {
		this.ailment_detail = ailment_detail;
	}

	public String getOther_drugs() {
		return other_drugs;
	}

	public void setOther_drugs(String other_drugs) {
		this.other_drugs = other_drugs;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getShade_newfield() {
		return shade_newfield;
	}

	public void setShade_newfield(String shade_newfield) {
		this.shade_newfield = shade_newfield;
	}

	public Integer getVisitId() {
		return VisitId;
	}

	public void setVisitId(Integer visitId) {
		VisitId = visitId;
	}
	
	
	

}
