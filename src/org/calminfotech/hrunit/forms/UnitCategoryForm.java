package org.calminfotech.hrunit.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class UnitCategoryForm {
	
private Integer UnitCategoryId;
	
	private Integer parentId;

	@NotEmpty(message = "Cannot be empty!")
	private String name;
	
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

	

	

	

}
