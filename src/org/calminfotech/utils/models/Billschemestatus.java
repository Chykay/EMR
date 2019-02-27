package org.calminfotech.utils.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Billschemestatus")
//@SQLDelete(sql = "UPDATE Billschemestatus SET is_deleted = 1 WHERE hmostatus_id = ?")
//@Where(clause = "is_deleted <> 1")
public class Billschemestatus {

	private Integer billschemestatus_id;
	private String name;
	private Date createdDate;
	private String CreatedBy;
	private String ModifiedBy;
   private Date modifiedDate;

	private Integer organisation_id;

	@Column(name = "created_by", nullable = false)
	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	@Column(name = "modified_by", nullable = false)
	public String getModifiedBy() {
		return ModifiedBy;
	}

	public Integer getOrganisation_id() {
		return organisation_id;
	}

	public void setOrganisation_id(Integer organisation_id) {
		this.organisation_id = organisation_id;
	}


	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billschemestatus_id", unique = true, nullable = false)

	public Integer getBillschemestatus_id() {
		return billschemestatus_id;
	}

	
	
	
	
	
	
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setBillschemestatus_id(Integer hmostatus_id) {
		this.billschemestatus_id = hmostatus_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "created_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "modified_date", nullable = true)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	//@ManyToOne
	
	
}
