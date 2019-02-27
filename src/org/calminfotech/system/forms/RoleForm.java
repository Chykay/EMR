package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;

public class RoleForm {

	private int roleId;

	@NotBlank(message = "Field cannot be empty!")
	private String roleName;

	private String roleDescription;

	private Integer admin;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
}