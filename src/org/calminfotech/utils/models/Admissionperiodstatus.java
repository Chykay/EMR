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
@Table(name = "Admissionperiodstatus")
@SQLDelete(sql = "UPDATE Admissionperiodstatus SET is_deleted = 1 WHERE status_id = ?")
//@Where(clause = "is_deleted <> 1")
public class Admissionperiodstatus {

	private Integer admissionperiodstatus_id;
	private String name;
	private Date createdDate;
	private String CreatedBy;
	private String ModifiedBy;
     private Date modifiedDate;

	private Integer organisation_id;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admissionperiodstatus_id", unique = true, nullable = false)

	public Integer getAdmissionperiodstatus_id() {
		return admissionperiodstatus_id;
	}

	

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public void setAdmissionperiodstatus_id(Integer admissionperiodstatus_id) {
		this.admissionperiodstatus_id = admissionperiodstatus_id;
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
	
	@Column(name = "created_by", nullable = false)
	public String getCreatedBy() {
		return CreatedBy;
	}

	
	public String getName() {
		return name;
	}
	
	
	
	
}
