package org.calminfotech.hrunit.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class HrunitCategoryForm {
	
private Integer categoryId;
	
	
	private String categoryDescription;
	
	
	private String categoryName;

	private boolean attendQ;
	
	private Integer billschemeId;
	
	private Integer point_id;
	
	private Integer pointstore_id;
	
	public Integer getBillschemeId() {
		return billschemeId;
	}

	public void setBillschemeId(Integer billschemeId) {
		this.billschemeId = billschemeId;
	}

	public Integer getPoint_id() {
		return point_id;
	}

	public void setPoint_id(Integer point_id) {
		this.point_id = point_id;
	}

	

	private Integer hrunitTypeId;

	
	private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private Boolean isDeleted;
   
	public boolean isAttendQ() {
		return attendQ;
	}

	public void setAttendQ(boolean attendQ) {
		this.attendQ = attendQ;
	}



	private Integer parentCategoryId;
	//Getters and Setters
	
	
	

	public String getCategoryName() {
		return categoryName;
	}

	public Integer getPointstore_id() {
		return pointstore_id;
	}

	public void setPointstore_id(Integer pointstore_id) {
		this.pointstore_id = pointstore_id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public Integer getHrunitTypeId() {
		return hrunitTypeId;
	}

	public void setHrunitTypeId(Integer hrunitTypeId) {
		this.hrunitTypeId = hrunitTypeId;
	}

	
	
}
