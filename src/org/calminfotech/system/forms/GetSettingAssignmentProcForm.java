package org.calminfotech.system.forms;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;


public class GetSettingAssignmentProcForm {
	/*@NotBlank(message = "Field cannot be empty!")
	private Integer setupId;*/
	
	
	//@NotBlank(message = "Field cannot be empty!")
	private Integer id;

	
	private String[] settings_code;


	private String[] settings_value;
	
	
	private String saveButton;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String[] getSettings_code() {
		return settings_code;
	}


	public void setSettings_code(String[] settings_code) {
		this.settings_code = settings_code;
	}


	public String[] getSettings_value() {
		return settings_value;
	}


	public void setSettings_value(String[] settings_value) {
		this.settings_value = settings_value;
	}


	public String getSaveButton() {
		return saveButton;
	}


	public void setSaveButton(String saveButton) {
		this.saveButton = saveButton;
	}

	
}
