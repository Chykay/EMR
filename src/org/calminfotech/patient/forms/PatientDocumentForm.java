package org.calminfotech.patient.forms;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class PatientDocumentForm {

	private Integer PatientDocumentId;

	private Integer patientId;

	@NotNull(message = "Pick a file")
	private MultipartFile Document;
	
	
	private String description;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public MultipartFile getDocument() {
		return Document;
	}

	public void setDocument(MultipartFile document) {
		Document = document;
	}

	


	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getPatientDocumentId() {
		return PatientDocumentId;
	}

	public void setPatientDocumentId(Integer patientDocumentId) {
		PatientDocumentId = patientDocumentId;
	}

	
	
}
