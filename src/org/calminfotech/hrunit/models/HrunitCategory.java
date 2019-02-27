package org.calminfotech.hrunit.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.system.models.BedCategory;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.hibernate.annotations.SQLDelete;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hrunit_category")
@SQLDelete(sql = "UPDATE hrunit_category SET is_deleted = 1 WHERE hrunit_category_id= ?")
//@Where(clause = "is_deleted <> 1")
public class HrunitCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hrunit_category_id", unique = true, nullable = false)
	private Integer categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "description")
	private String description;

	@Column(name = "parentcategory_id")
	private Integer parentCategoryId;

	@Column(name = "pointstore_id")
	private Integer pointstoreId;
	
	@ManyToOne
	@JoinColumn(name = "hrunittype_id")
	private HrunitType hrunitTypeId;
	
	@ManyToOne
	@JoinColumn(name = "point_id")
	private VisitWorkflowPoint point;
	
	
	/*
	@ManyToOne
	@JoinColumn(name = "billing_scheme_id")
	private BillScheme billingScheme;
	*/
	@OneToMany
	@JoinColumn(name = "hrunitcategory_id")
	private Set <BedCategory> bedcategory;


		
	
	
	@Column(name = "queue")
	private Boolean attendQ;
	

	
	
	public Integer getPointstoreId() {
		return pointstoreId;
	}

	public void setPointstoreId(Integer pointstoreId) {
		this.pointstoreId = pointstoreId;
	}

	public Boolean isAttendQ(Boolean b) {
		return attendQ;
	}

	public VisitWorkflowPoint getPoint() {
		return point;
	}

	public void setPoint(VisitWorkflowPoint point) {
		this.point = point;
	}

	@Column(name = "organisation_id")
	private Integer organisationId;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;
/*
	public BillScheme getBillingScheme() {
		return billingScheme;
	}

	public void setBillingScheme(BillScheme billingScheme) {
		this.billingScheme = billingScheme;
	}
*/
	

	

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	
	// Setters and Getters

	
	public Boolean getAttendQ() {
		return attendQ;
	}

	public void setAttendQ(Boolean attendQ) {
		this.attendQ = attendQ;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}


	public HrunitType getHrunitTypeId() {
		return hrunitTypeId;
	}

	public void setHrunitTypeId(HrunitType hrunitTypeId) {
		this.hrunitTypeId = hrunitTypeId;
	}


	

	public Set<BedCategory> getBedcategory() {
		return bedcategory;
	}

	public void setBedcategory(Set<BedCategory> bedcategory) {
		this.bedcategory = bedcategory;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

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

	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
	

	

}
