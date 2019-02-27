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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "surgery_category")
@SQLDelete(sql = "UPDATE surgery_category SET is_deleted = 1 WHERE surgery_category_id= ?")
@Where(clause = "is_deleted <> 1")
public class SurgeryCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "surgery_category_id", unique = true, nullable = false)
	private Integer categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "description")
	private String description;

	@Column(name = "parentcategory_id")
	private Integer parentCategoryId;

	
	
	@ManyToOne
	@JoinColumn(name = "surgerytype_id")
	private SurgeryType surgeryTypeId;
	



	@OneToMany
	@JoinColumn(name = "surgery_category_id")
	private Set<Surgery> surgery;


	
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


	public SurgeryType getSurgeryTypeId() {
		return surgeryTypeId;
	}

	public void setSurgeryTypeId(SurgeryType surgeryTypeId) {
		this.surgeryTypeId = surgeryTypeId;
	}

	public Set<Surgery> getSurgery() {
		return surgery;
	}

	public void setSurgery(Set<Surgery> surgery) {
		this.surgery = surgery;
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
