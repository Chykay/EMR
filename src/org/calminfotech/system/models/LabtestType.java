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
@Table(name = "labtest_type")
public class LabtestType {
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "labtest_type_id", unique = true, nullable = false)
	private Integer labtestTypeId;

	@Column(name = "labtesttype_name", nullable = true)
	private String labtesttypename;

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
	@JoinColumn(name = "labtest_type_id")
	private Set<LabtestCategory> labtestcategory;
	

	@OneToMany
	@JoinColumn(name = "labtest_type_id")
	private Set<Labtest> labtest;





	public String getLabtesttypename() {
		return labtesttypename;
	}


	public void setLabtesttypename(String labtesttypename) {
		this.labtesttypename = labtesttypename;
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


	public Integer getLabtestTypeId() {
		return labtestTypeId;
	}


	public void setLabtestTypeId(Integer labtestTypeId) {
		this.labtestTypeId = labtestTypeId;
	}


	public Set<LabtestCategory> getLabtestcategory() {
		return labtestcategory;
	}


	public void setLabtestcategory(Set<LabtestCategory> labtestcategory) {
		this.labtestcategory = labtestcategory;
	}


	public Set<Labtest> getLabtest() {
		return labtest;
	}


	public void setLabtest(Set<Labtest> labtest) {
		this.labtest = labtest;
	}



	
}