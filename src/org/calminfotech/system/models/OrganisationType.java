package org.calminfotech.system.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.system.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "organisation_type")
public class OrganisationType {
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organisation_type_id", unique = true, nullable = false)
	private Integer organisationTypeId;

	@Column(name = "organisationtype_name", nullable = true)
	private String organisationtypename;

	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "created_date", nullable = true)
	private Date createdDate;
	
	@Column(name = "created_by", nullable = true)
	private String createdBy;
	
	@Column(name = "modified_date", nullable = true)
	private String modifiedDate;
	
	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;
	
	@Column(name = "is_deleted", nullable = true)
	private boolean isDeleted;

	

	@OneToMany
	@JoinColumn(name = "organisation_type_id")
	private Set<OrganisationCategory> organisationcategory;
	

	@OneToMany
	@JoinColumn(name = "organisation_type_id")
	private Set<Organisation> organisation;





	public String getOrganisationtypename() {
		return organisationtypename;
	}


	public void setOrganisationtypename(String organisationtypename) {
		this.organisationtypename = organisationtypename;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public String getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(String modifiedDate) {
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


	public Integer getOrganisationTypeId() {
		return organisationTypeId;
	}


	public void setOrganisationTypeId(Integer organisationTypeId) {
		this.organisationTypeId = organisationTypeId;
	}


	public Set<OrganisationCategory> getOrganisationcategory() {
		return organisationcategory;
	}


	public void setOrganisationcategory(Set<OrganisationCategory> organisationcategory) {
		this.organisationcategory = organisationcategory;
	}


	public Set<Organisation> getOrganisation() {
		return organisation;
	}


	public void setOrganisation(Set<Organisation> organisation) {
		this.organisation = organisation;
	}



	
}