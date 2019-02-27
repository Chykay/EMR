package org.calminfotech.system.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class BedForm {

	private Integer bedId;
	
	private Integer bedroomId;

	private String bedName;
		
	
	private String bedDescription;
	
	
	private Integer bedTypeId;

	
private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer parentCategoryId;
	
	private Integer status_id;
	
	
	
	public Integer getBedspaceId() {
		return bedroomId;
	}

	public void setBedspaceId(Integer bedroomId) {
		this.bedroomId = bedroomId;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

	public Integer getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}

	
	//Getters and Setters


	public void setBedDescription(String BedDescription) {
		this.bedDescription = BedDescription;
	}
	


	public Integer getBedId() {
		return bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public String getBedName() {
		return bedName;
	}

	public void setBedName(String bedName) {
		this.bedName = bedName;
	}

	public Integer getBedtypeId() {
		return bedTypeId;
	}

	public void setBedtypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}

	public String getBedDescription() {
		return bedDescription;
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
