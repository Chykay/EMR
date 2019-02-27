package org.calminfotech.system.forms;

public class XrayTypeForm {
	private Integer xrayTypeId;
	private String xrayname;
	private String description;
	//getters and setters

	
	public String getDescription() {
		return description;
	}
	public String getXrayname() {
		return xrayname;
	}
	public void setXrayname(String xrayname) {
		this.xrayname = xrayname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getXrayTypeId() {
		return xrayTypeId;
	}
	public void setXrayTypeId(Integer xrayTypeId) {
		this.xrayTypeId = xrayTypeId;
	}
}

