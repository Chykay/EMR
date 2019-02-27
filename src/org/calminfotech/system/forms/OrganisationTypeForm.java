package org.calminfotech.system.forms;

public class OrganisationTypeForm {
	private Integer organisationTypeId;
	private String organisationname;
	private String description;
	//getters and setters

	
	public String getDescription() {
		return description;
	}
	public String getOrganisationname() {
		return organisationname;
	}
	public void setOrganisationname(String organisationname) {
		this.organisationname = organisationname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOrganisationTypeId() {
		return organisationTypeId;
	}
	public void setOrganisationTypeId(Integer organisationTypeId) {
		this.organisationTypeId = organisationTypeId;
	}
}

