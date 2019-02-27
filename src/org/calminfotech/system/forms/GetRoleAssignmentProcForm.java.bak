package org.calminfotech.system.forms;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class GetRoleAssignmentProcForm {

	@NotBlank(message = "Field cannot be empty!")
	private Integer pRole;
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer permissionId;

	@NotBlank(message = "Field cannot be empty!")
	private Integer organisationId;

	@NotBlank(message = "Field cannot be empty!")
	private String description;

	private String[] permissionCheckboxVals;
	
	private String subOrder;
	
	private String saveButton;

	public Integer getpRole() {
		return pRole;
	}

	public void setpRole(Integer pRole) {
		this.pRole = pRole;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(String subOrder) {
		this.subOrder = subOrder;
	}

	public String getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(String saveButton) {
		this.saveButton = saveButton;
	}

	public String[] getPermissionCheckboxVals() {
		return permissionCheckboxVals;
	}

	public void setPermissionCheckboxVals(String[] permissionCheckboxVals) {
		this.permissionCheckboxVals = permissionCheckboxVals;
	}

}
