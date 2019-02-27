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
import javax.persistence.Table;

//import org.calminfotech.patient.models.PatientAllergy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Labtest")
@SQLDelete(sql = "UPDATE labtest SET is_deleted = 1 WHERE labtest_id= ?")
//@Where(clause = "is_deleted <> 1")
public class Labtest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "labtest_id", unique = true, nullable = false)
	private Integer labtestId;

	@Column(name = "labtest_name", nullable = false)
	private String labtestName;

	@Column(name = "description", nullable = false)
	private String description;


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

	


	


	

	public String getLabtestName() {
		return labtestName;
	}

	public void setLabtestName(String labtestName) {
		this.labtestName = labtestName;
	}

	public void setLabtestType(LabtestType labtestType) {
		this.labtestType = labtestType;
	}

	@ManyToOne
	@JoinColumn(name = "labtest_type_id")
	private LabtestType labtestType;
	
	public LabtestType getLabtestType() {
		return labtestType;
	}

	public void setLabtestTypeId(LabtestType labtestType) {
		this.labtestType = labtestType;
	}

	@ManyToOne
	@JoinColumn(name = "labtest_category_id")
	private LabtestCategory labtestCategory;

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

	public Integer getLabtestId() {
		return labtestId;
	}

	public void setLabtestId(Integer labtestId) {
		this.labtestId = labtestId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LabtestCategory getLabtestCategory() {
		return labtestCategory;
	}

	public void setLabtestCategory(LabtestCategory labtestCategory) {
		this.labtestCategory = labtestCategory;
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
