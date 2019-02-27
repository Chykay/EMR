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
@Table(name = "xray_type")
public class XrayType {
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "xraytype_id", unique = true, nullable = false)
	private Integer xrayTypeId;

	@Column(name = "xraytype_name", nullable = true)
	private String xraytypename;

	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "created_date", nullable = true)
	private Date createdDate;
	
	@Column(name = "created_by", nullable = true)
	private String createdBy;
	
	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;
	
	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;
	
	@Column(name = "is_deleted", nullable = true)
	private boolean isDeleted;

	

	@OneToMany
	@JoinColumn(name = "xraytype_id")
	private Set<XrayCategory> xraycategory;
	

	@OneToMany
	@JoinColumn(name = "xraytype_id")
	private Set<Xray> xray;





	public String getXraytypename() {
		return xraytypename;
	}


	public void setXraytypename(String xraytypename) {
		this.xraytypename = xraytypename;
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


	public Integer getXrayTypeId() {
		return xrayTypeId;
	}


	public void setXrayTypeId(Integer xrayTypeId) {
		this.xrayTypeId = xrayTypeId;
	}


	public Set<XrayCategory> getXraycategory() {
		return xraycategory;
	}


	public void setXraycategory(Set<XrayCategory> xraycategory) {
		this.xraycategory = xraycategory;
	}


	public Set<Xray> getXray() {
		return xray;
	}


	public void setXray(Set<Xray> xray) {
		this.xray = xray;
	}



	
}