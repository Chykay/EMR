package org.calminfotech.hrunit.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class HrunitForm {

	private Integer hrunitId;
	
	
	private String hrunitName;
		
	
	private String hrunitDescription;
	
	
	private Integer hrunitTypeId;

	
private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer parentCategoryId;
	
	
	public Integer getHrunitTypeId() {
		return hrunitTypeId;
	}

	public void setHrunitTypeId(Integer hrunitTypeId) {
		this.hrunitTypeId = hrunitTypeId;
	}

	
	//Getters and Setters


	public void setHrunitDescription(String HrunitDescription) {
		this.hrunitDescription = HrunitDescription;
	}
	


	public Integer getHrunitId() {
		return hrunitId;
	}

	public void setHrunitId(Integer hrunitId) {
		this.hrunitId = hrunitId;
	}

	public String getHrunitName() {
		return hrunitName;
	}

	public void setHrunitName(String hrunitName) {
		this.hrunitName = hrunitName;
	}

	public Integer getHrunittypeId() {
		return hrunitTypeId;
	}

	public void setHrunittypeId(Integer hrunitTypeId) {
		this.hrunitTypeId = hrunitTypeId;
	}

	public String getHrunitDescription() {
		return hrunitDescription;
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
