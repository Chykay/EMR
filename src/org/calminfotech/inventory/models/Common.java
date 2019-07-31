package org.calminfotech.inventory.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.calminfotech.system.models.Organisation;

@MappedSuperclass
public class Common {

	@Column(name = "create_date")
	// @Temporal(TemporalType.DATE)
	protected Date createDate;

	@Column(name = "created_by")
	protected String createdBy;

	@Column(name = "modified_by")
	protected String modifiedBy;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	protected Organisation organisation;

	
	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@Column(name = "modified_date", nullable = true)
	// @Temporal(TemporalType.DATE)
	protected Date modifiedDate;

	@Column(name = "is_deleted")
	protected boolean isDeleted = false;

	@Column(name = "is_active")
	protected Boolean active = true;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getActive() {
		return active;
	}

}
