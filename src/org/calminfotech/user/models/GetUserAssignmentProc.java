package org.calminfotech.user.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(name = "spGetUserAssignment",
query = "{CALL sp_user_assignment(:userid,:orgid)}", 
callable = true, 
resultClass = GetUserAssignmentProc.class)
@org.hibernate.annotations.Entity(dynamicInsert = true)
//@Table(name = "usr_permissions_setup")
public class GetUserAssignmentProc implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_id", unique = true, nullable = false)
	private Integer permissionId;

	
	
	@Column(name = "permission_code", nullable = true)
	private String permissionCode;
	@Column(name = "category")
	private String category;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	/*@Column(name = "organisation_id")
	private Integer organisation;*/
	
	@Column(name = "status", nullable = true)
	private boolean checkStatus;
	//getter and setter

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

/*	public Integer getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Integer organisation) {
		this.organisation = organisation;
	}*/

	public boolean isCheckStatus() {
		return checkStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	
	
}
