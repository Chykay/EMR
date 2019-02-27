package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.Range;

public class SettingsForm {

	
	private Integer id;
	

	private String name;

	private String category;

	private String saveButton;


	
	private String[]  settings_value;


	private String[]  settings_code;

	
	
	public String getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(String saveButton) {
		this.saveButton = saveButton;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String[] getSettings_value() {
		return settings_value;
	}

	public void setSettings_value(String[] settings_value) {
		this.settings_value = settings_value;
	}

	public String[] getSettings_code() {
		return settings_code;
	}

	public void setSettings_code(String[] settings_code) {
		this.settings_code = settings_code;
	}

	
}
