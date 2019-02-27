package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class GroupFormFieldForm {

	@Range(min = 0, message = "System Error!")
	private Integer groupId;

	private Integer fieldId;

	@NotBlank(message = "Label field cannot be empty!")
	private String fieldLabel;

	@Range(min = 1, message = "Select an Input type")
	private int fieldInputType;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public int getFieldInputType() {
		return fieldInputType;
	}

	public void setFieldInputType(int fieldInputType) {
		this.fieldInputType = fieldInputType;
	}

}
