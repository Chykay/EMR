package org.calminfotech.system.forms;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class GroupActionsForm {

	
	private Integer groupId;
	
	@NotEmpty(message="Please select, at least, an action")
	private Integer[] actions;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer[] getActions() {
		return actions;
	}

	public void setActions(Integer[] actions) {
		this.actions = actions;
	}

}
