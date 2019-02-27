package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;

public class UnitToItemForm {

	@NotBlank(message = "Field cannot be empty!")
	private Integer itemId;
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer unitId;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
}
