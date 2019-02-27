package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class VisitStatusForm {

	private Integer id;

	@NotEmpty(message = "Field cannot be empty")
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
