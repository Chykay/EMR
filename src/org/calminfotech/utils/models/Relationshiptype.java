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
@Table(name = "Relationshiptype")
@SQLDelete(sql = "UPDATE Relationshiptype SET is_deleted = 1 WHERE relationshiptype_id = ?")
//@Where(clause = "is_deleted <> 1")
public class Relationshiptype {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "relationshiptype_id", unique = true, nullable = false)
	private Integer relationshiptypeId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	@Column(name = "modified_by", nullable = false)
	private String ModifiedBy;
	
	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	@Column(name = "organnisation_id")
	private Integer organisation_id;

	@Column(name = "is_deleted")
	private Integer is_deleted;

	
	public Integer getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Integer is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Integer getRelationshiptypeId() {
		return relationshiptypeId;
	}

	public void setRelationshiptypeId(Integer relationshiptypeId) {
		this.relationshiptypeId = relationshiptypeId;
	}

	public String getName() {
		return name;
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

	public String getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getOrganisation_id() {
		return organisation_id;
	}

	public void setOrganisation_id(Integer organisation_id) {
		this.organisation_id = organisation_id;
	}
	
		
}
