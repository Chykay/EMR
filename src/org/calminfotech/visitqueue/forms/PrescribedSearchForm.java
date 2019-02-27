package org.calminfotech.visitqueue.forms;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

public class PrescribedSearchForm {

	private Integer id;
	
	private String dat1;
	
	
	private String dat2;
	
	private Integer userId;
	
	private Integer patientId;
	
 private Integer statusId;
 
 private String patientName;
 
 private Integer hmoId;
 


	private Integer parentCategoryId;
	
	

public Integer getParentCategoryId() {
		return parentCategoryId;
	}


	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}


public Integer getHmoId() {
	return hmoId;
}


public void setHmoId(Integer hmoId) {
	this.hmoId = hmoId;
}


public String getPatientName() {
	return patientName;
}


public void setPatientName(String patientName) {
	this.patientName = patientName;
}


public Integer getUserId() {
	return userId;
}


public void setUserId(Integer userId) {
	this.userId = userId;
}


public Integer getPatientId() {
	return patientId;
}


public void setPatientId(Integer patientId) {
	this.patientId = patientId;
}


public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}



public String getDat1() {
	return dat1;
}


public void setDat1(String dat1) {
	this.dat1 = dat1;
}


public String getDat2() {
	return dat2;
}


public void setDat2(String dat2) {
	this.dat2 = dat2;
}


public Integer getStatusId() {
	return statusId;
}


public void setStatusId(Integer statusId) {
	this.statusId = statusId;
}




 
 
}
