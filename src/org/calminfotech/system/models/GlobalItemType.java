package org.calminfotech.system.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "globalitem_type")
public class GlobalItemType {
	// variables
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "globalitemtype_id", unique = true, nullable = false)
	private Integer globalitemTypeId;


	@Column(name = "globalitemtype_name", nullable = true)
	private String globalitemtypename;

	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "created_date", nullable = true)
	private Date createdDate;
	
	@Column(name = "created_by", nullable = true)
	private String createdBy;
	
	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;
	
	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;
	
	@Column(name = "is_deleted", nullable = true)
	private Boolean isDeleted;

	

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<GlobalItemKind> getGlobalItemKind() {
		return globalItemKind;
	}

	public void setGlobalItemKind(Set<GlobalItemKind> globalItemKind) {
		this.globalItemKind = globalItemKind;
	}

	@OneToMany
	@JoinColumn(name = "globalitemtype_id")
	private Set<GlobalItemCategory> globalItemCategory;
	

	@OneToMany
	@JoinColumn(name = "globalitemtype_id")
	private Set<GlobalItem> globalItem;

	@OneToMany
	@JoinColumn(name = "globalitemtype_id")
	private Set<GlobalItemKind> globalItemKind;
	
	
	public String getGlobalitemtypename() {
		return globalitemtypename;
	}

	public void setGlobalitemtypename(String globalitemtypename) {
		this.globalitemtypename = globalitemtypename;
	}

	public Set<GlobalItemCategory> getGlobalItemCategory() {
		return globalItemCategory;
	}

	public void setGlobalItemCategory(Set<GlobalItemCategory> globalItemCategory) {
		this.globalItemCategory = globalItemCategory;
	}


	// getters and setters
	public Integer getGlobalitemTypeId() {
		return globalitemTypeId;
	}

	public void setGlobalitemTypeId(Integer globalitemTypeId) {
		this.globalitemTypeId = globalitemTypeId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

public void setModifiedDate(Date modifiedDate) {
	this.modifiedDate = modifiedDate;
}

public Set<GlobalItem> getGlobalItem() {
	return globalItem;
}

public void setGlobalItem(Set<GlobalItem> globalItem) {
	this.globalItem = globalItem;
}
}