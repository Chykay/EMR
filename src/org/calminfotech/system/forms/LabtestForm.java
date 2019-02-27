package org.calminfotech.system.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class LabtestForm {

	private Integer labtestId;
	
	
	private String labtestName;
		
	
	private String labtestDescription;
	
	
	private Integer labtestTypeId;

	
private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer parentCategoryId;
	
	
	public Integer getLabtestTypeId() {
		return labtestTypeId;
	}

	public void setLabtestTypeId(Integer labtestTypeId) {
		this.labtestTypeId = labtestTypeId;
	}

	
	//Getters and Setters


	public void setLabtestDescription(String LabtestDescription) {
		this.labtestDescription = LabtestDescription;
	}
	


	public Integer getLabtestId() {
		return labtestId;
	}

	public void setLabtestId(Integer labtestId) {
		this.labtestId = labtestId;
	}

	public String getLabtestName() {
		return labtestName;
	}

	public void setLabtestName(String labtestName) {
		this.labtestName = labtestName;
	}

	public Integer getLabtesttypeId() {
		return labtestTypeId;
	}

	public void setLabtesttypeId(Integer labtestTypeId) {
		this.labtestTypeId = labtestTypeId;
	}

	public String getLabtestDescription() {
		return labtestDescription;
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
