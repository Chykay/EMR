package org.calminfotech.user.forms;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class PermissionForm {

	private Integer permissionId;

	@NotBlank(message = "Field cannot be empty!")
	private String description;
	
	@NotBlank(message = "Field cannot be empty!")
	private String[] chckdeval;

	@NotBlank(message = "Field cannot be empty!")
	private Integer pRole;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getpRole() {
		return pRole;
	}

	public void setpRole(Integer pRole) {
		this.pRole = pRole;
	}

	public String[] getChckdeval() {
		return chckdeval;
	}

	public void setChckdeval(String[] chckdeval) {
		this.chckdeval = chckdeval;
	}
}
