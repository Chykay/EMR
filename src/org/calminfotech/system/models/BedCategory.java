package org.calminfotech.system.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.calminfotech.hrunit.models.HrunitCategory;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bed_category")
@SQLDelete(sql = "UPDATE bed_category SET is_deleted = 1 WHERE bed_category_id= ?")
// @Where(clause = "is_deleted <> 1")
public class BedCategory {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bed_category_id", unique = true, nullable = false)
	private Integer categoryId;

	@OneToOne
	@PrimaryKeyJoinColumn
	private GlobalItem bedroomitem;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "description")
	private String description;

	@Column(name = "parentcategory_id")
	private Integer parentCategoryId;

	@Column(name = "bedpoints")
	private Integer bedpoints;

	@ManyToOne
	@JoinColumn(name = "bedtype_id")
	private BedType bedTypeId;

	@ManyToOne
	@JoinColumn(name = "hrunitcategory_id")
	private HrunitCategory hrunitcategory;

	public GlobalItem getBedroomitem() {
		return bedroomitem;
	}

	public void setBedroomitem(GlobalItem bedroomitem) {
		this.bedroomitem = bedroomitem;
	}

	public Integer getBedpoints() {
		return bedpoints;
	}

	public void setBedpoints(Integer bedpoints) {
		this.bedpoints = bedpoints;
	}

	@OneToMany
	@Where(clause = "is_deleted <> 1")
	@JoinColumn(name = "bed_category_id")
	private Set<Bed> bed;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
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

	// Setters and Getters

	public HrunitCategory getHrunitcategory() {
		return hrunitcategory;
	}

	public void setHrunitcategory(HrunitCategory hrunitcategory) {
		this.hrunitcategory = hrunitcategory;
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

	public BedType getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(BedType bedTypeId) {
		this.bedTypeId = bedTypeId;
	}

	public Set<Bed> getBed() {
		return bed;
	}

	public void setBed(Set<Bed> bed) {
		this.bed = bed;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
