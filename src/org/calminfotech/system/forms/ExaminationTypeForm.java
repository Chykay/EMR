package org.calminfotech.system.forms;

public class ExaminationTypeForm {
	private Integer allergyTypeId;
	private String allergyname;
	private String description;
	//getters and setters

	
	public String getDescription() {
		return description;
	}
	public String getExaminationname() {
		return allergyname;
	}
	public void setExaminationname(String allergyname) {
		this.allergyname = allergyname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getExaminationTypeId() {
		return allergyTypeId;
	}
	public void setExaminationTypeId(Integer allergyTypeId) {
		this.allergyTypeId = allergyTypeId;
	}
}

