package org.calminfotech.system.forms;

public class SurgeryTypeForm {
	private Integer surgeryTypeId;
	private String surgeryname;
	private String description;
	//getters and setters

	
	public String getDescription() {
		return description;
	}
	public String getSurgeryname() {
		return surgeryname;
	}
	public void setSurgeryname(String surgeryname) {
		this.surgeryname = surgeryname;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSurgeryTypeId() {
		return surgeryTypeId;
	}
	public void setSurgeryTypeId(Integer surgeryTypeId) {
		this.surgeryTypeId = surgeryTypeId;
	}
}

