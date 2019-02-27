package org.calminfotech.user.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "usr_permissions_setup")
public class Permission implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer permissionId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "authcode")
	private String authcode;
/*
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

*/
	
	
/*
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usr_permission_relationship", joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
	private List<Permission> category = new ArrayList<Permission>();
	*/
	
	
	@Column(name = "activation")
	private boolean activation;
	
	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	@Column(name = "status")
	private boolean status;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "usr_role_permission_assignment", 
	joinColumns = { @JoinColumn(name = "permission_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> role = new HashSet<Role>();

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "usr_user_permission_assignment",
	joinColumns = { @JoinColumn(name = "permission_id")},
	inverseJoinColumns = { @JoinColumn(name = "user_id")})
	private Set<User> user = new HashSet<User>();

	
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

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

	/*
	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

*/
	/*
	public List<Permission> getCategory() {
		return category;
	}

	public void setCategory(List<Permission> category) {
		this.category = category;
	}
	*/
	
	public boolean isActivation() {
		return activation;
	}

	public void setActivation(boolean activation) {
		this.activation = activation;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
}
