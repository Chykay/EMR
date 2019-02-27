package org.calminfotech.system.forms;

public class BedTypeForm {
	private Integer bedTypeId;
	private String bedname;
	private String description;
	//getters and setters

	
	public String getDescription() {
		return description;
	}
	public String getExaminationname() {
		return bedname;
	}
	public void setExaminationname(String bedname) {
		this.bedname = bedname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getExaminationTypeId() {
		return bedTypeId;
	}
	public void setExaminationTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}
}

