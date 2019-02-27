package org.calminfotech.hrunit.forms;

public class HrunitTypeForm {
	private Integer hrunitTypeId;
	private String hrunitname;
	private String description;
	//getters and setters

	
	public String getDescription() {
		return description;
	}
	public String getHrunitname() {
		return hrunitname;
	}
	public void setHrunitname(String hrunitname) {
		this.hrunitname = hrunitname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getHrunitTypeId() {
		return hrunitTypeId;
	}
	public void setHrunitTypeId(Integer hrunitTypeId) {
		this.hrunitTypeId = hrunitTypeId;
	}
}

