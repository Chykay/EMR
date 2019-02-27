package org.calminfotech.system.forms;

public class DrugsTypeForm {
	private Integer drugsTypeId;
	private String drugsname;
	private String description;
	//getters and setters

	
	public String getDescription() {
		return description;
	}
	public String getDrugsname() {
		return drugsname;
	}
	public void setDrugsname(String drugsname) {
		this.drugsname = drugsname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDrugsTypeId() {
		return drugsTypeId;
	}
	public void setDrugsTypeId(Integer drugsTypeId) {
		this.drugsTypeId = drugsTypeId;
	}
}

