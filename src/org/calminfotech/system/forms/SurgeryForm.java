package org.calminfotech.system.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class SurgeryForm {

	private Integer surgeryId;
	
	
	private String surgeryName;
		
	
	private String surgeryDescription;
	
	
	private Integer surgeryTypeId;

	
private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer parentCategoryId;
	
	
	public Integer getSurgeryTypeId() {
		return surgeryTypeId;
	}

	public void setSurgeryTypeId(Integer surgeryTypeId) {
		this.surgeryTypeId = surgeryTypeId;
	}

	
	//Getters and Setters


	public void setSurgeryDescription(String SurgeryDescription) {
		this.surgeryDescription = SurgeryDescription;
	}
	


	public Integer getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}

	public String getSurgeryName() {
		return surgeryName;
	}

	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}

	public Integer getSurgerytypeId() {
		return surgeryTypeId;
	}

	public void setSurgerytypeId(Integer surgeryTypeId) {
		this.surgeryTypeId = surgeryTypeId;
	}

	public String getSurgeryDescription() {
		return surgeryDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	
}
