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
@Table(name = "BillschemeType")
@SQLDelete(sql = "UPDATE billschemetype SET is_deleted = 1 WHERE billschemetype_id = ?")
//@Where(clause = "is_deleted <> 1")
public class Billschemetype {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billschemetype_id", unique = true, nullable = false)
	private Integer billschemetypeId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	@Column(name = "modified_by", nullable = true)
	private String ModifiedBy;
	
	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	private Integer organisation_id;
	
	
	
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

	

	public String getName() {
		return name;
	}




	public Integer getBillschemetypeId() {
		return billschemetypeId;
	}


	public void setBillschemetypeId(Integer billschemetypeId) {
		this.billschemetypeId = billschemetypeId;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	//@ManyToOne
	
	
}
