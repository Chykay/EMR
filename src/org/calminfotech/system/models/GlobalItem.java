package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
//
//import org.calminfotech.consultation.models.VisitConsultationPrescribedDrug;
//import org.calminfotech.consultation.models.VisitConsultationPrescribedLabtest;
//import org.calminfotech.inventory.models.StockOpeningBalance;
//import org.calminfotech.laboratory.models.LabResultSetup;
//import org.calminfotech.laboratory.models.LabTestDocument;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "globalitem_item")
@SQLDelete(sql = "UPDATE globalitem_item SET is_deleted = 1 WHERE item_id= ?")
//@Where(clause = "is_deleted <> 1")
public class GlobalItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	private Integer itemId;

	@Column(name = "globalitem_name", nullable = false)
	private String GlobalitemName;

	@Column(name = "globalitem_code", nullable = false)
	private String globalitemcode;
	
	
	

	@Column(name = "licenseno", nullable = false)
	private String licenseno;
	
	@Column(name = "description", nullable = false)
	private String description;
    
	
	@ManyToOne
	@JoinColumn(name = "globalitemkind_code")
	private GlobalItemKind globalitemkind;
	 
	
	
	
    @ManyToOne
	@JoinColumn(name = "globalitemtype_id")
	private GlobalItemType globalitemtype;
	
	@ManyToOne
	@JoinColumn(name = "item_category_id")
	private GlobalItemCategory globalItemCategory;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Bed bed;
	
	
	
	@OneToMany
	@JoinColumn(name = "globalitem_id")
	@Where(clause = "is_deleted <> 1")
	private Set<GlobalItemUnitofMeasure> globalitemunit;
	
	
//	@OneToMany
//	@JoinColumn(name = "test_id")
//	@Where(clause = "is_deleted <> 1")
//	private Set<VisitConsultationPrescribedLabtest> prescribedlabtest;
//
//	
//	
//	@OneToMany
//	@JoinColumn(name = "drugs_id")
//	@Where(clause = "is_deleted <> 1")
//	private Set<VisitConsultationPrescribedDrug> prescribeddrug;
//	
//	
//	@OneToMany
//	@JoinColumn(name = "test_id")
//	@Where(clause = "is_deleted <> 1")
//	private Set<LabResultSetup> labresultsetup;
//	
//	
	
	
	
	
	/*
	@OneToMany
	@JoinColumn(name = "resultmeasure_id")
	@Where(clause = "is_deleted <> 1")
	private Set<GlobalUnitofMeasure> globalmeasure;
	
	*/
	

	

//	public void setPrescribedlabtest(
//			Set<VisitConsultationPrescribedLabtest> prescribedlabtest) {
//		this.prescribedlabtest = prescribedlabtest;
//	}

	public String getGlobalitemcode() {
		return globalitemcode;
	}

	public void setGlobalitemcode(String globalitemcode) {
		this.globalitemcode = globalitemcode;
	}

	public GlobalItemKind getGlobalitemkind() {
		return globalitemkind;
	}

	public void setGlobalitemkind(GlobalItemKind globalitemkind) {
		this.globalitemkind = globalitemkind;
	}

	public Bed getBed() {
		return bed;
	}

	public void setBed(Bed bed) {
		this.bed = bed;
	}
//
//	public Set<VisitConsultationPrescribedLabtest> getPrescribedlabtest() {
//		return prescribedlabtest;
//	}
//
//	public Set<VisitConsultationPrescribedDrug> getPrescribeddrug() {
//		return prescribeddrug;
//	}
//
//	public void setPrescribeddrug(
//			Set<VisitConsultationPrescribedDrug> prescribeddrug) {
//		this.prescribeddrug = prescribeddrug;
//	}
//
//	public Set<LabResultSetup> getLabresultsetup() {
//		return labresultsetup;
//	}
//
//	public void setLabresultsetup(Set<LabResultSetup> labresultsetup) {
//		this.labresultsetup = labresultsetup;
//	}
//
//	public void setLabresultseup(Set<LabResultSetup> labresultseup) {
//		this.labresultsetup = labresultseup;
//	}

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	
	@ManyToMany
	@JoinTable(name = "globalitem_unitofmeasure", joinColumns = { @JoinColumn(name = "globalitem_id") }, inverseJoinColumns = { @JoinColumn(name = "unitofmeasure_id") })
	private List<GlobalUnitofMeasure> measurement;


	/*
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "hmo_subservice_item", joinColumns = { @JoinColumn(name = "globalitem_item_id") }, inverseJoinColumns = { @JoinColumn(name = "hmo_subservice_id") })
	private Set<HmoPckSubService> hmoPackSubService = new HashSet<HmoPckSubService>();

	@ManyToMany
	@JoinTable(name = "globalitem_point", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "point_id") })
	private List<VisitWorkflowPoint> globalItemPoint;

*/

	
	public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

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

	
	
	public Set<GlobalItemUnitofMeasure> getGlobalitemunit() {
		return globalitemunit;
	}

	public void setGlobalitemunit(Set<GlobalItemUnitofMeasure> globalitemunit) {
		this.globalitemunit = globalitemunit;
	}

	
	public GlobalItemType getGlobalitemtype() {
		return globalitemtype;
	}

	public void setGlobalitemtype(GlobalItemType globalitemtype) {
		this.globalitemtype = globalitemtype;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	

	public List<GlobalUnitofMeasure> getMeasurement() {
		return measurement;
	}

	public void setMeasurement(List<GlobalUnitofMeasure> measurement) {
		this.measurement = measurement;
	}


	/*
	public Set<HmoPckSubService> getHmoPackSubService() {
		return hmoPackSubService;
	}

	public void setHmoPackSubService(Set<HmoPckSubService> hmoPackSubService) {
		this.hmoPackSubService = hmoPackSubService;
	}
	

	public List<VisitWorkflowPoint> getGlobalItemPoint() {
		return globalItemPoint;
	}

	public void setGlobalItemPoint(List<VisitWorkflowPoint> globalItemPoint) {
		this.globalItemPoint = globalItemPoint;
	}
*/


	public GlobalItemCategory getGlobalItemCategory() {
		return globalItemCategory;
	}

	public void setGlobalItemCategory(GlobalItemCategory globalItemCategory) {
		this.globalItemCategory = globalItemCategory;
	}


	public String getGlobalitemName() {
		return GlobalitemName;
	}

	public void setGlobalitemName(String globalitemName) {
		GlobalitemName = globalitemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * public int getParentCatgoryId() { return parentCatgoryId; }
	 * 
	 * public void setParentCatgoryId(int parentCatgoryId) {
	 * this.parentCatgoryId = parentCatgoryId; }
	 */



	
	
	
	
	
	

	public String getCreatedBy() {
		return createdBy;
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
