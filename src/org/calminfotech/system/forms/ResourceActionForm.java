package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.Range;

public class ResourceActionForm {

	@Range(min = 1)
	private Integer resourceId;
	
	@Range(min = 1)
	private Integer groupId;

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
