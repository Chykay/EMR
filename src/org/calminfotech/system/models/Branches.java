package org.calminfotech.system.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.user.models.Role;
import org.hibernate.annotations.SQLDelete;

//import java.sql.Date;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "branches")
@SQLDelete(sql = "UPDATE branches SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class Branches {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer Id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@Column(name = "is_deleted")
	private boolean isDelete = false;

	@OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
	// @JoinColumn(name = "organisation_id")
	private Set<Role> role;

	@Column(name = "created_date")
	private Date createdDate;

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	@Column(name = "active")
	private boolean active;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;

	@Column(name = "image_content_type", nullable = true)
	private String imageContentType;

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageContentType() {
		return imageContentType;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/*
	 * public Set<OrganisationDirector> getDirector() { return director; }
	 * 
	 * public void setDirector(Set<OrganisationDirector> director) {
	 * this.director = director; }
	 */

	public Integer getId() {
		return Id;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/*
	 * public Set<User> getUsers() { return users; }
	 * 
	 * public void setUsers(Set<User> user) { this.users = user; }
	 */

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
