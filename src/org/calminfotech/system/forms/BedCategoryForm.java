package org.calminfotech.system.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class BedCategoryForm {
	
private Integer categoryId;
	
	
	private String categoryDescription;
	
	
	private String categoryName;

	
	private Integer bedTypeId;

	private Integer hrunitcategoryId;
	
	private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer parentCategoryId;
	//Getters and Setters
	
	
	private Integer bedpoints;
	

	public Integer getBedpoints() {
		return bedpoints;
	}

	public void setBedpoints(Integer bedpoints) {
		this.bedpoints = bedpoints;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Integer getHrunitcategoryId() {
		return hrunitcategoryId;
	}

	public void setHrunitcategoryId(Integer hrunitcategoryId) {
		this.hrunitcategoryId = hrunitcategoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
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

	public Integer getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}

	
	
}
