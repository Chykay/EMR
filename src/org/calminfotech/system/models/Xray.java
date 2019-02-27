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
@Table(name = "Xray")
@SQLDelete(sql = "UPDATE xray SET is_deleted = 1 WHERE xray_id= ?")
//@Where(clause = "is_deleted <> 1")
public class Xray {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "xray_id", unique = true, nullable = false)
	private Integer xrayId;

	@Column(name = "xray_name", nullable = false)
	private String xrayName;

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

	


	


	

	public String getXrayName() {
		return xrayName;
	}

	public void setXrayName(String xrayName) {
		this.xrayName = xrayName;
	}

	public void setXrayType(XrayType xrayType) {
		this.xrayType = xrayType;
	}

	@ManyToOne
	@JoinColumn(name = "xraytype_id")
	private XrayType xrayType;
	
	public XrayType getXrayType() {
		return xrayType;
	}

	public void setXrayTypeId(XrayType xrayType) {
		this.xrayType = xrayType;
	}

	@ManyToOne
	@JoinColumn(name = "xray_category_id")
	private XrayCategory xrayCategory;

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


	

	public String getCreatedBy() {
		return createdBy;
	}

	public Integer getXrayId() {
		return xrayId;
	}

	public void setXrayId(Integer xrayId) {
		this.xrayId = xrayId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public XrayCategory getXrayCategory() {
		return xrayCategory;
	}

	public void setXrayCategory(XrayCategory xrayCategory) {
		this.xrayCategory = xrayCategory;
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
