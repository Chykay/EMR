package org.calminfotech.system.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class ExaminationForm {

	private Integer examinationId;
	
	
	private String examinationName;
		
	
	private String examinationDescription;
	
	
	private Integer examinationTypeId;

	
private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer parentCategoryId;
	
	
	public Integer getExaminationTypeId() {
		return examinationTypeId;
	}

	public void setExaminationTypeId(Integer examinationTypeId) {
		this.examinationTypeId = examinationTypeId;
	}

	
	//Getters and Setters


	public void setExaminationDescription(String ExaminationDescription) {
		this.examinationDescription = ExaminationDescription;
	}
	


	public Integer getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(Integer examinationId) {
		this.examinationId = examinationId;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public Integer getExaminationtypeId() {
		return examinationTypeId;
	}

	public void setExaminationtypeId(Integer examinationTypeId) {
		this.examinationTypeId = examinationTypeId;
	}

	public String getExaminationDescription() {
		return examinationDescription;
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
