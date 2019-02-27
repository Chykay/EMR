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
@Table(name = "globalitem_reorder")
public class GlobalItemReorder {
	// variables
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "globalitemreorder_id", unique = true, nullable = false)
	private Integer globalitemReorderId;

	

	@Column(name = "reorderqty")
	private Integer reorderqty;
	
	
	public Integer getGlobalitemReorderId() {
		return globalitemReorderId;
	}

	public void setGlobalitemReorderId(Integer globalitemReorderId) {
		this.globalitemReorderId = globalitemReorderId;
	}

	public Integer getReorderqty() {
		return reorderqty;
	}

	public void setReorderqty(Integer reorderqty) {
		this.reorderqty = reorderqty;
	}

	public GlobalItem getGlobalitem() {
		return globalitem;
	}

	public void setGlobalitem(GlobalItem globalitem) {
		this.globalitem = globalitem;
	}

	

	@ManyToOne
	@JoinColumn(name = "globalitem_id")
	private GlobalItem  globalitem;
	


	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
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

}