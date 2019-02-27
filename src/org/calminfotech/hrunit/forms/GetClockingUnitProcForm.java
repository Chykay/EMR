package org.calminfotech.hrunit.forms;

import java.util.Date;

import javax.persistence.Column;

public class GetClockingUnitProcForm {
	
	private Integer userId;

	private Integer unitId;
	
	private Integer hours;
	
	private Integer minutes;
	
	private String[] unitCheckboxVals;
	
	private String subOrder;
	
	private String saveButton;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

	
	public String[] getUnitCheckboxVals() {
		return unitCheckboxVals;
	}

	public void setUnitCheckboxVals(String[] unitCheckboxVals) {
		this.unitCheckboxVals = unitCheckboxVals;
	}

	public String getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(String subOrder) {
		this.subOrder = subOrder;
	}

	public String getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(String saveButton) {
		this.saveButton = saveButton;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

}
