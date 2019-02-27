package org.calminfotech.system.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class DrugsForm {

	private Integer drugsId;
	
	
	private String drugsName;
		
	
	private String drugsDescription;
	
	
	private Integer drugsTypeId;

	
private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer parentCategoryId;
	
	
	public Integer getDrugsTypeId() {
		return drugsTypeId;
	}

	public void setDrugsTypeId(Integer drugsTypeId) {
		this.drugsTypeId = drugsTypeId;
	}

	
	//Getters and Setters


	public void setDrugsDescription(String DrugsDescription) {
		this.drugsDescription = DrugsDescription;
	}
	


	public Integer getDrugsId() {
		return drugsId;
	}

	public void setDrugsId(Integer drugsId) {
		this.drugsId = drugsId;
	}

	public String getDrugsName() {
		return drugsName;
	}

	public void setDrugsName(String drugsName) {
		this.drugsName = drugsName;
	}

	public Integer getDrugstypeId() {
		return drugsTypeId;
	}

	public void setDrugstypeId(Integer drugsTypeId) {
		this.drugsTypeId = drugsTypeId;
	}

	public String getDrugsDescription() {
		return drugsDescription;
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
