package org.calminfotech.user.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.system.models.OrganisationCompany;
import org.hibernate.annotations.SQLDelete;

//query = "from Table t left join User u where u.id= :id"
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
// @org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@Table(name = "usr_roles_setup")
@SQLDelete(sql = "UPDATE usr_roles_setup SET is_deleted = 1 WHERE UserId = ?")
public class Role implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9160881819374280018L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int roleId;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_description", nullable = true)
	private String roleDescription;

	@Column(name = "is_admin", nullable = true)
	private boolean isAdmin;

	@Column(name = "is_deleted", nullable = true)
	private boolean isDeleted;

	@Column(name = "is_deletable", nullable = true)
	private boolean isDeletable;

	@Column(name = "created_date", nullable = true)
	private Date createdDate;

	@Column(name = "created_by", nullable = true)
	private String createdBy;

	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private OrganisationCompany organisation;

	@ManyToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private Set<Permission> permission = new HashSet<Permission>();

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private Set<User> user = new HashSet<User>();

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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public OrganisationCompany getOrganisation() {
		return organisation;
	}

	public void setOrganisation(OrganisationCompany organisation) {
		this.organisation = organisation;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isDeletable() {
		return isDeletable;
	}

	public void setDeletable(boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date date) {
		this.modifiedDate = date;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set<Permission> getPermission() {
		return permission;
	}

	public void setPermission(Set<Permission> permission) {
		this.permission = permission;
	}

}