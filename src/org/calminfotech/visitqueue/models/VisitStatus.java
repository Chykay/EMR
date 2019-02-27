package org.calminfotech.visitqueue.models;

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
@Table(name = "visit_statuses")
@SQLDelete(sql = "UPDATE visit_statuses SET is_deleted = 1 WHERE id = ?")
//@Where(clause = "is_deleted <> 1")
public class VisitStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "type")
	private String type;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "is_start_point")
	private boolean isStartPoint;

	@Column(name = "is_end_point")
	private boolean isEndPoint;

	
	@Column(name = "organisation_id")
	private int organisationId;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the isStartPoint
	 */
	public boolean isStartPoint() {
		return isStartPoint;
	}

	/**
	 * @param isStartPoint
	 *            the isStartPoint to set
	 */
	public void setStartPoint(boolean isStartPoint) {
		this.isStartPoint = isStartPoint;
	}

	/**
	 * @return the isEndPoint
	 */
	public boolean isEndPoint() {
		return isEndPoint;
	}

	/**
	 * @param isEndPoint
	 *            the isEndPoint to set
	 */
	public void setEndPoint(boolean isEndPoint) {
		this.isEndPoint = isEndPoint;
	}

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	/**
	 * @return the organisation
	 */
	
}
