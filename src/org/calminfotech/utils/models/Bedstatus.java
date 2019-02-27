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
@Table(name = "Bedstatus")
@SQLDelete(sql = "UPDATE Bedstatus SET is_deleted = 1 WHERE bedstatus_id = ?")
//@Where(clause = "is_deleted <> 1")
public class Bedstatus {

	private Integer bedstatus_id;
	private String name;
	private Date createdDate;
	private String CreatedBy;
	private String ModifiedBy;
	
    public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

private Date modifiedDate;
   private boolean is_deleted;

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
	@Column(name = "bedstatus_id", unique = true, nullable = false)

	public Integer getBedstatus_id() {
		return bedstatus_id;
	}

	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setBedstatus_id(Integer bedstatus_id) {
		this.bedstatus_id = bedstatus_id;
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
