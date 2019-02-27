package org.calminfotech.visitqueue.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class VisitStatusForm {

	private Integer id;

	@NotEmpty(message = "Field cannot be empty")
	
	private String donedate;
	
	private String comments; 
	
	
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

	public String getDonedate() {
		return donedate;
	}

	public void setDonedate(String donedate) {
		this.donedate = donedate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
}
