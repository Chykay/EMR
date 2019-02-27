package org.calminfotech.hrunit.models;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import org.calminfotech.consultation.models.VisitConsultationPrescribedLabtest;
//import org.calminfotech.consultation.models.VisitConsultationPrescribedSurgery;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Staff_document")
public class SurgeryDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "description")
	private String description;

	@Column(name = "file_document")
	private Blob file;

	@Column(name = "name")
	private String name;

	
	@Column(name = "content_type")
	private String contentType;

	@Column(name = "create_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "is_deleted")
	private Boolean is_deleted;
//	
//	@ManyToOne
//	@JoinColumn(name = "consultation_prescribed_id")
//	private VisitConsultationPrescribedSurgery prescribedsurgery;

	
	@Column(name = "organisation_id")
	private Integer organisationId;

	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}


//	public VisitConsultationPrescribedSurgery getPrescribedsurgery() {
//		return prescribedsurgery;
//	}
//
//	public void setPrescribedsurgery(
//			VisitConsultationPrescribedSurgery prescribedsurgery) {
//		this.prescribedsurgery = prescribedsurgery;
//	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	

}
