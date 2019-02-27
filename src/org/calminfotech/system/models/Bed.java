package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.calminfotech.utils.models.Bedstatus;
import org.hibernate.annotations.SQLDelete;

//import org.calminfotech.patient.models.PatientBed;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Bed")
@SQLDelete(sql = "UPDATE bed SET is_deleted = 1 WHERE bed_id= ?")
// @Where(clause = "is_deleted <> 1")
public class Bed {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bed_id", unique = true, nullable = false)
	private Integer bedId;

	@OneToOne
	@PrimaryKeyJoinColumn
	private GlobalItem beditem;
	// @JoinColumn(name = "bed_id", nullable = false)
	//

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

	@ManyToOne
	@JoinColumn(name = "bedstatus_id")
	private Bedstatus bedstatus;

	public GlobalItem getBeditem() {
		return beditem;
	}

	public void setBeditem(GlobalItem beditem) {
		this.beditem = beditem;
	}

	public Bedstatus getBedstatus() {
		return bedstatus;
	}

	public void setBedstatus(Bedstatus bedstatus) {
		this.bedstatus = bedstatus;
	}

	public Integer getBedId() {
		return bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	@ManyToOne
	@JoinColumn(name = "bed_category_id")
	private BedCategory bedCategory;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "organisation_id") private Organisation organisation;
	 */

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
	 * @ManyToMany
	 * 
	 * @JoinTable(name = "globalitem_unitofmeasure", joinColumns = {
	 * 
	 * @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name
	 * = "unit_of_measure_id") }) private List<GlobalUnitofMeasure> measurement;
	 */

	/*
	 * @ManyToMany(cascade = { CascadeType.ALL })
	 * 
	 * @JoinTable(name = "hmo_subservice_item", joinColumns = { @JoinColumn(name
	 * = "globalitem_item_id") }, inverseJoinColumns = { @JoinColumn(name =
	 * "hmo_subservice_id") }) private Set<HmoPckSubService> hmoPackSubService =
	 * new HashSet<HmoPckSubService>();
	 * 
	 * @ManyToMany
	 * 
	 * @JoinTable(name = "globalitem_point", joinColumns = { @JoinColumn(name =
	 * "item_id") }, inverseJoinColumns = { @JoinColumn(name = "point_id") })
	 * private List<VisitWorkflowPoint> diseasePoint;
	 */

	/*
	 * public Set<HmoPckSubService> getHmoPackSubService() { return
	 * hmoPackSubService; }
	 * 
	 * public void setHmoPackSubService(Set<HmoPckSubService> hmoPackSubService)
	 * { this.hmoPackSubService = hmoPackSubService; }
	 * 
	 * 
	 * public List<VisitWorkflowPoint> getDiseasePoint() { return diseasePoint;
	 * }
	 * 
	 * public void setDiseasePoint(List<VisitWorkflowPoint> diseasePoint) {
	 * this.diseasePoint = diseasePoint; }
	 */

	public String getCreatedBy() {
		return createdBy;
	}

	/*
	 * public Integer getBedId() { return bedId; }
	 * 
	 * public void setBedId(Integer bedId) { this.bedId = bedId; }
	 */

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BedCategory getBedCategory() {
		return bedCategory;
	}

	public void setBedCategory(BedCategory bedCategory) {
		this.bedCategory = bedCategory;
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
