package org.calminfotech.system.forms;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class XrayForm {

	private Integer xrayId;
	
	
	private String xrayName;
		
	
	private String xrayDescription;
	
	
	private Integer xrayTypeId;

	
private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer parentCategoryId;
	
	
	public Integer getXrayTypeId() {
		return xrayTypeId;
	}

	public void setXrayTypeId(Integer xrayTypeId) {
		this.xrayTypeId = xrayTypeId;
	}

	
	//Getters and Setters


	public void setXrayDescription(String XrayDescription) {
		this.xrayDescription = XrayDescription;
	}
	


	public Integer getXrayId() {
		return xrayId;
	}

	public void setXrayId(Integer xrayId) {
		this.xrayId = xrayId;
	}

	public String getXrayName() {
		return xrayName;
	}

	public void setXrayName(String xrayName) {
		this.xrayName = xrayName;
	}

	public Integer getXraytypeId() {
		return xrayTypeId;
	}

	public void setXraytypeId(Integer xrayTypeId) {
		this.xrayTypeId = xrayTypeId;
	}

	public String getXrayDescription() {
		return xrayDescription;
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
