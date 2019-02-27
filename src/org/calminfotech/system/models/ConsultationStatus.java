package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "consultation_statuses")
@SQLDelete(sql = "UPDATE consultation_statuses SET is_deleted = 1 WHERE consultation_status_id = ?")
@Where(clause = "is_deleted <> 1")
public class ConsultationStatus {

	private int id;
	private String type;
	private Date createdDate;
	private String createdBy;

	private boolean isStartPoint;
	private boolean isEndPoint;

	private Organisation organisation;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consultation_status_id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne
	@JoinColumn(name = "organisation_id") 
	public Organisation getOrganisation() { 
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@Column(name = "is_start_point")
	public boolean isStartPoint() {
		return isStartPoint;
	}

	public void setStartPoint(boolean isStartPoint) {
		this.isStartPoint = isStartPoint;
	} 

	@Column(name = "is_end_point")
	public boolean isEndPoint() {
		return isEndPoint;
	}

	public void setEndPoint(boolean isEndPoint) {
		this.isEndPoint = isEndPoint;
	}

}
