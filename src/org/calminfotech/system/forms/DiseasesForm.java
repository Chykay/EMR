package org.calminfotech.system.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class DiseasesForm {

	private Integer diseasesId;
	
	
	private String diseasesName;
		
	
	private String diseasesDescription;
	
	
	private Integer diseasesTypeId;

	
private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer parentCategoryId;
	
	
	public Integer getDiseasesTypeId() {
		return diseasesTypeId;
	}

	public void setDiseasesTypeId(Integer diseasesTypeId) {
		this.diseasesTypeId = diseasesTypeId;
	}

	
	//Getters and Setters


	public void setDiseasesDescription(String DiseasesDescription) {
		this.diseasesDescription = DiseasesDescription;
	}
	


	public Integer getDiseasesId() {
		return diseasesId;
	}

	public void setDiseasesId(Integer diseasesId) {
		this.diseasesId = diseasesId;
	}

	public String getDiseasesName() {
		return diseasesName;
	}

	public void setDiseasesName(String diseasesName) {
		this.diseasesName = diseasesName;
	}

	public Integer getDiseasestypeId() {
		return diseasesTypeId;
	}

	public void setDiseasestypeId(Integer diseasesTypeId) {
		this.diseasesTypeId = diseasesTypeId;
	}

	public String getDiseasesDescription() {
		return diseasesDescription;
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
