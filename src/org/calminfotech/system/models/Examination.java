package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import javax.persistence.Table;

import org.calminfotech.patient.models.PatientTelephone;
//import org.hibernate.annotations.OrderBy;
//import org.calminfotech.patient.models.PatientAllergy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Examination")
@SQLDelete(sql = "UPDATE examination SET is_deleted = 1 WHERE examination_id= ?")
//@Where(clause = "is_deleted <> 1")
public class Examination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "examination_id", unique = true, nullable = false)
	private Integer examinationId;

	@Column(name = "examination_name", nullable = false)
	private String examinationName;

	@Column(name = "description", nullable = false)
	private String description;


	@OneToMany
	@JoinColumn(name = "examination_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<ExaminationResultSetup> examinationResultSetup;

    
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	



	  
	public Set<ExaminationResultSetup> getExaminationResultSetup() {
		return examinationResultSetup;
	}

	public void setExaminationResultSetup(
			Set<ExaminationResultSetup> examinationResultSetup) {
		this.examinationResultSetup = examinationResultSetup;
	}	


	

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public void setExaminationType(ExaminationType examinationType) {
		this.examinationType = examinationType;
	}

	@ManyToOne
	@JoinColumn(name = "examinationtype_id")
	private ExaminationType examinationType;
	
	public ExaminationType getExaminationType() {
		return examinationType;
	}

	public void setExaminationTypeId(ExaminationType examinationType) {
		this.examinationType = examinationType;
	}

	@ManyToOne
	@JoinColumn(name = "examination_category_id")
	private ExaminationCategory examinationCategory;

/*	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;*/
	
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	/*
	@ManyToMany
	@JoinTable(name = "globalitem_unitofmeasure", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "unit_of_measure_id") })
	private List<GlobalUnitofMeasure> measurement;
*/

	/*
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "hmo_subservice_item", joinColumns = { @JoinColumn(name = "globalitem_item_id") }, inverseJoinColumns = { @JoinColumn(name = "hmo_subservice_id") })
	private Set<HmoPckSubService> hmoPackSubService = new HashSet<HmoPckSubService>();

	@ManyToMany
	@JoinTable(name = "globalitem_point", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "point_id") })
	private List<VisitWorkflowPoint> diseasePoint;

*/

	
	/*
	public Set<HmoPckSubService> getHmoPackSubService() {
		return hmoPackSubService;
	}

	public void setHmoPackSubService(Set<HmoPckSubService> hmoPackSubService) {
		this.hmoPackSubService = hmoPackSubService;
	}
	

	public List<VisitWorkflowPoint> getDiseasePoint() {
		return diseasePoint;
	}

	public void setDiseasePoint(List<VisitWorkflowPoint> diseasePoint) {
		this.diseasePoint = diseasePoint;
	}
*/
	
	

	public String getCreatedBy() {
		return createdBy;
	}

	public Integer getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(Integer examinationId) {
		this.examinationId = examinationId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExaminationCategory getExaminationCategory() {
		return examinationCategory;
	}

	public void setExaminationCategory(ExaminationCategory examinationCategory) {
		this.examinationCategory = examinationCategory;
	}

	

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	

	

}
