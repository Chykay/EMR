package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class PatientSurgicalHistoryForm {
	
	private Integer id;
	
	private Integer patientId;
	
	private Integer VisitId;
	
	private Integer surgicalProceduresid;
	
	private Integer surgeryyear;
	
	private String anasthetic_complication;
	
	private String complication_detail;
	
	@NotBlank(message = "Field cannot be empty!")
	private String surgery_organisation;
	
	private String organisation_address;
	
	@NotBlank(message = "Field cannot be empty!")
	@Email(message = "Invalid email format")
	private String organisation_email;
	
	private String organisation_phone;
	
	private String surgeon_full_name;

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

	public Integer getSurgicalProceduresid() {
		return surgicalProceduresid;
	}

	public void setSurgicalProceduresid(Integer surgicalProceduresid) {
		this.surgicalProceduresid = surgicalProceduresid;
	}
	
	public String getAnasthetic_complication() {
		return anasthetic_complication;
	}

	public void setAnasthetic_complication(String anasthetic_complication) {
		this.anasthetic_complication = anasthetic_complication;
	}

	public String getComplication_detail() {
		return complication_detail;
	}

	public void setComplication_detail(String complication_detail) {
		this.complication_detail = complication_detail;
	}

	public String getSurgery_organisation() {
		return surgery_organisation;
	}

	public void setSurgery_organisation(String surgery_organisation) {
		this.surgery_organisation = surgery_organisation;
	}

	public String getOrganisation_address() {
		return organisation_address;
	}

	public void setOrganisation_address(String organisation_address) {
		this.organisation_address = organisation_address;
	}

	public String getOrganisation_email() {
		return organisation_email;
	}

	public void setOrganisation_email(String organisation_email) {
		this.organisation_email = organisation_email;
	}

	public String getOrganisation_phone() {
		return organisation_phone;
	}

	public void setOrganisation_phone(String organisation_phone) {
		this.organisation_phone = organisation_phone;
	}

	public String getSurgeon_full_name() {
		return surgeon_full_name;
	}

	public void setSurgeon_full_name(String surgeon_full_name) {
		this.surgeon_full_name = surgeon_full_name;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	public Integer getSurgeryyear() {
		return surgeryyear;
	}

	public void setSurgeryyear(Integer surgeryyear) {
		this.surgeryyear = surgeryyear;
	}

	public Integer getVisitId() {
		return VisitId;
	}

	public void setVisitId(Integer visitId) {
		VisitId = visitId;
	}
}
