package org.calminfotech.system.forms;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class OrganisationDocumentForm {

	private Integer id;

	private Integer organisationId;

	@NotNull(message = "Pick a file")
	private MultipartFile document;

	public MultipartFile getDocument() {
		return document;
	}

	public void setDocument(MultipartFile document) {
		this.document = document;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
