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
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "section_point")
public class LoginSectionPoint {
	
	private Integer id;
	
	private LoginSection loginSection;
	
	private VisitWorkflowPoint workflowPoint;

	private String createdBy;
	
	private Date createdDate;
		
	private Organisation organisation;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "session_id")
	public LoginSection getLoginSection() {
		return loginSection;
	}

	public void setLoginSection(LoginSection loginSection) {
		this.loginSection = loginSection;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@ManyToOne
	@JoinColumn(name = "point_id")
	public VisitWorkflowPoint getWorkflowPoint() {
		return workflowPoint;
	}

	public void setWorkflowPoint(VisitWorkflowPoint workflowPoint) {
		this.workflowPoint = workflowPoint;
	}
}
