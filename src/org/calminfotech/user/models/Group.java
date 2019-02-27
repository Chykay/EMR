package org.calminfotech.user.models;

import java.sql.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Resource;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "groups")
@SQLDelete(sql = "UPDATE groups SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class Group {

	private int id;
	private String groupName;
	private String groupDescription;
	private Date createdDate;
	private boolean isAdmin;
	private boolean isDeleted;
	private boolean isDeletable;

	private String createdBy;
	private Date modifiedDate;
	private Set<UserAction> userActions = new HashSet<UserAction>();

	private Set<GroupFormField> groupFormFields = new HashSet<GroupFormField>();

	private Set<Resource> resources = new HashSet<Resource>();

	/*private Organisation organisation;*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "group_name", nullable = false)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "group_description", nullable = true)
	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	@Column(name = "created_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		if (createdDate != null)
			this.createdDate = createdDate;
	}

	@Column(name = "created_by", nullable = false)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "is_admin")
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(name = "is_deleted")
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "is_deletable")
	public boolean isDeletable() {
		return isDeletable;
	}

	public void setDeletable(boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	@Column(name = "modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "group_actions", 
	joinColumns = { @JoinColumn(name = "group_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "action_id") })
	public Set<UserAction> getUserActions() {
		return userActions;
	}

	public void setUserActions(Set<UserAction> userActions) {
		this.userActions = userActions;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "group_id")
	public Set<GroupFormField> getGroupFormFields() {
		return groupFormFields;
	}

	public void setGroupFormFields(Set<GroupFormField> groupFormFields) {
		this.groupFormFields = groupFormFields;
	}
/*
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
*/
	@ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}
