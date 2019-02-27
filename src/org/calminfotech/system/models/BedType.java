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


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bed_type")
public class BedType {
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bedtype_id", unique = true, nullable = false)
	private Integer bedTypeId;

	@Column(name = "bedtype_name", nullable = true)
	private String bedtypename;

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
	@JoinColumn(name = "bedtype_id")
	private Set<BedCategory> bedcategory;
	

	@OneToMany
	@JoinColumn(name = "bedtype_id")
	private Set<Bed> bed;





	public String getBedtypename() {
		return bedtypename;
	}


	public void setBedtypename(String bedtypename) {
		this.bedtypename = bedtypename;
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


	public Integer getBedTypeId() {
		return bedTypeId;
	}


	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}


	public Set<BedCategory> getBedcategory() {
		return bedcategory;
	}


	public void setBedcategory(Set<BedCategory> bedcategory) {
		this.bedcategory = bedcategory;
	}


	public Set<Bed> getBed() {
		return bed;
	}


	public void setBed(Set<Bed> bed) {
		this.bed = bed;
	}



	
}