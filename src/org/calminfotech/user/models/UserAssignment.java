package org.calminfotech.user.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.OrganisationCompany;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(name = "spGetDeleteUserCheckedVal", query = "{CALL sp_delete_user_checked_values(:userid)}", callable = true, resultClass = UserAssignment.class)
@Table(name = "usr_user_permission_assignment")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class UserAssignment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "user_id", unique = false, nullable = true)
	private Integer userId;

	@Column(name = "permission_id", nullable = true)
	private Integer permissionId;

	@Column(name = "permission_code", nullable = true)
	private String permissionCode;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private OrganisationCompany organisation;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	public OrganisationCompany getOrganisation() {
		return organisation;
	}

	public void setOrganisation(OrganisationCompany organisation) {
		this.organisation = organisation;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	// getter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
}
