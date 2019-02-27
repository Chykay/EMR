package org.calminfotech.system.forms;

public class DiseasesTypeForm {
	private Integer diseasesTypeId;
	private String diseasesname;
	private String description;
	//getters and setters

	
	public String getDescription() {
		return description;
	}
	public String getDiseasesname() {
		return diseasesname;
	}
	public void setDiseasesname(String diseasesname) {
		this.diseasesname = diseasesname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDiseasesTypeId() {
		return diseasesTypeId;
	}
	public void setDiseasesTypeId(Integer diseasesTypeId) {
		this.diseasesTypeId = diseasesTypeId;
	}
}

