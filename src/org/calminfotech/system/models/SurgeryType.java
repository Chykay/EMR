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
@Table(name = "surgery_type")
public class SurgeryType {
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "surgerytype_id", unique = true, nullable = false)
	private Integer surgeryTypeId;

	@Column(name = "surgerytype_name", nullable = true)
	private String surgerytypename;

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
	@JoinColumn(name = "surgerytype_id")
	private Set<SurgeryCategory> surgerycategory;
	

	@OneToMany
	@JoinColumn(name = "surgerytype_id")
	private Set<Surgery> surgery;





	public String getSurgerytypename() {
		return surgerytypename;
	}


	public void setSurgerytypename(String surgerytypename) {
		this.surgerytypename = surgerytypename;
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


	public Integer getSurgeryTypeId() {
		return surgeryTypeId;
	}


	public void setSurgeryTypeId(Integer surgeryTypeId) {
		this.surgeryTypeId = surgeryTypeId;
	}


	public Set<SurgeryCategory> getSurgerycategory() {
		return surgerycategory;
	}


	public void setSurgerycategory(Set<SurgeryCategory> surgerycategory) {
		this.surgerycategory = surgerycategory;
	}


	public Set<Surgery> getSurgery() {
		return surgery;
	}


	public void setSurgery(Set<Surgery> surgery) {
		this.surgery = surgery;
	}



	
}