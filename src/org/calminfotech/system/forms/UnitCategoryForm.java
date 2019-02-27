package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class UnitCategoryForm {
	
private Integer UnitCategoryId;
	
	private Integer parentId;

	@NotEmpty(message = "Cannot be empty!")
	private String name;
	
	private boolean attendQ;
	
	private String billingScheme;
	
	private int pointId;

	public Integer getUnitCategoryId() {
		return UnitCategoryId;
	}

	public void setUnitCategoryId(Integer unitCategoryId) {
		UnitCategoryId = unitCategoryId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAttendQ() {
		return attendQ;
	}

	public void setAttendQ(boolean attendQ) {
		this.attendQ = attendQ;
	}

	public String getBillingScheme() {
		return billingScheme;
	}

	public void setBillingScheme(String billingScheme) {
		this.billingScheme = billingScheme;
	}

	

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	

	

}
