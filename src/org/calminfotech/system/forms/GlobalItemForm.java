package org.calminfotech.system.forms;

import java.util.Date;

import javax.validation.constraints.Size;

import org.calminfotech.system.models.GlobalItemType;

public class GlobalItemForm {
	
	private Integer GlobalitemId;
	
	

	private Integer reorderqty;
	
	private String GlobalitemName;
	
	private String globalitemcode;
	
	private String licenseno;
	private String GlobalitemDescription;
	
	//private Integer globalkindId;
	 
	private String globalitemkindcode;
	
	
	private Integer globaltypeId;

	
	private String createdBy;
	
	private Date createdDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private boolean isDeleted;
   
	private Integer itemCategoryId;
	//Getters and Setters

	

	public String getGlobalitemName() {
		return GlobalitemName;
	}

	public Integer getReorderqty() {
		return reorderqty;
	}

	public void setReorderqty(Integer reorderqty) {
		this.reorderqty = reorderqty;
	}

	public String getGlobalitemcode() {
		return globalitemcode;
	}

	public void setGlobalitemcode(String globalitemcode) {
		this.globalitemcode = globalitemcode;
	}

	public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public String getGlobalitemkindcode() {
		return globalitemkindcode;
	}

	public void setGlobalitemkindcode(String globalitemkindcode) {
		this.globalitemkindcode = globalitemkindcode;
	}

	public Integer getGlobalitemId() {
		return GlobalitemId;
	}

	public void setGlobalitemId(Integer globalitemId) {
		GlobalitemId = globalitemId;
	}

	/*
	public Integer getGlobalkindId() {
		return globalkindId;
	}

	public void setGlobalkindId(Integer globalkindId) {
		this.globalkindId = globalkindId;
	}
*/
	public void setGlobalitemName(String globalitemName) {
		GlobalitemName = globalitemName;
	}

	public String getGlobalitemDescription() {
		return GlobalitemDescription;
	}

	public void setGlobalitemDescription(String globalitemDescription) {
		GlobalitemDescription = globalitemDescription;
	}

	public Integer getGlobaltypeId() {
		return globaltypeId;
	}

	public void setGlobaltypeId(Integer globaltypeId) {
		this.globaltypeId = globaltypeId;
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

	public Integer getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(Integer itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}
	

}
