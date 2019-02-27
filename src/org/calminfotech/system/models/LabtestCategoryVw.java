package org.calminfotech.system.models;

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

import org.calminfotech.system.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "vw_labtest_cat")
public class LabtestCategoryVw {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "labtest_category_id", unique = true, nullable = false)
	private Integer categoryId;

	@Column(name = "category_name", nullable = false)
	private String categoryName;

	@Column(name = "description", nullable = false)
	private String description;

	

	public String getLabtestType() {
		return labtestType;
	}

	public void setLabtestType(String labtestType) {
		this.labtestType = labtestType;
	}
	@Column(name = "parent_name", nullable = true)
	private String parentName;

	@Column(name = "labtest_type", nullable = false)
	private String labtestType; 
	
	/*@ManyToOne
	@JoinColumn(name = "globalitemtype_id")
	private GlobalItemType itemTypeId;*/
/*
	@Column(name = "parent_category_id", nullable = true)
	private String parentCategoryId;
*/
	/*@OneToMany
	@JoinColumn(name = "item_category_id")
	private Set<GlobalItem> globalItem;
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
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

*/
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	/*
	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
*/
	
/*
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	

	@Column(name = "modified_by")
	private String modifiedBy;
*/
	@Column(name = "is_deleted")
	private boolean isDeleted;

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// Setters and Getters



	
	
	
}
