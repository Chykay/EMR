package org.calminfotech.hrunit.models;

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
@Table(name = "hrunit_type")
public class HrunitType {
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hrunittype_id", unique = true, nullable = false)
	private Integer hrunitTypeId;

	@Column(name = "hrunittype_name", nullable = true)
	private String hrunittypename;

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
	private boolean isDeleted;

	

	@OneToMany
	@JoinColumn(name = "hrunittype_id")
	private Set<HrunitCategory> hrunitcategory;
	

	@OneToMany
	@JoinColumn(name = "hrunittype_id")
	private Set<Hrunit> hrunit;





	public String getHrunittypename() {
		return hrunittypename;
	}


	public void setHrunittypename(String hrunittypename) {
		this.hrunittypename = hrunittypename;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public Integer getHrunitTypeId() {
		return hrunitTypeId;
	}


	public void setHrunitTypeId(Integer hrunitTypeId) {
		this.hrunitTypeId = hrunitTypeId;
	}


	public Set<HrunitCategory> getHrunitcategory() {
		return hrunitcategory;
	}


	public void setHrunitcategory(Set<HrunitCategory> hrunitcategory) {
		this.hrunitcategory = hrunitcategory;
	}


	public Set<Hrunit> getHrunit() {
		return hrunit;
	}


	public void setHrunit(Set<Hrunit> hrunit) {
		this.hrunit = hrunit;
	}



	
}