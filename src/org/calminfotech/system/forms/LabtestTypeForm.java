package org.calminfotech.system.forms;

public class LabtestTypeForm {
	private Integer labtestTypeId;
	private String labtestname;
	private String description;
	//getters and setters

	
	public String getDescription() {
		return description;
	}
	public String getLabtestname() {
		return labtestname;
	}
	public void setLabtestname(String labtestname) {
		this.labtestname = labtestname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getLabtestTypeId() {
		return labtestTypeId;
	}
	public void setLabtestTypeId(Integer labtestTypeId) {
		this.labtestTypeId = labtestTypeId;
	}
}

