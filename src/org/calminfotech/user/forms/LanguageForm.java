package org.calminfotech.user.forms;

import org.hibernate.validator.constraints.NotBlank;

public class LanguageForm {

	private Integer id;
	
	@NotBlank(message = "Field cannot be blank!")
	private String name;
	

	
	
	
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
	
	

	
	
}
