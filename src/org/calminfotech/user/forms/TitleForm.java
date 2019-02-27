package org.calminfotech.user.forms;

import org.hibernate.validator.constraints.NotBlank;

public class TitleForm {

	private Integer id;

	@NotBlank(message = "Field cannot be blank!")
	private String acronym;
	
	@NotBlank(message = "Field cannot be blank!")
	private String title;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

}
