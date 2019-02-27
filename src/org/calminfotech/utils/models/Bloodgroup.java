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
@Table(name = "Bloodgroup")
@SQLDelete(sql = "UPDATE Bloodgroup SET is_deleted = 1 WHERE bloodgroup_id = ?")
//@Where(clause = "is_deleted <> 1")
public class Bloodgroup {

	private Integer bloodgroup_id;
	private String name;
	private Date createdDate;
	private String CreatedBy;
	private String ModifiedBy;
    
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

	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	private Date modifiedDate;

	private Integer organisation_id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bloodgroup_id", unique = true, nullable = false)
	public Integer getBloodgroup_id() {
		return bloodgroup_id;
	}

	public void setBloodgroup_id(Integer Bloodgroup_id) {
		this.bloodgroup_id = Bloodgroup_id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
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
