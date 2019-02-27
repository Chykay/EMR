package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Sys_Settings")
public class SysSettings {
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer Id;

	@Column(name = "settings_code", nullable = true)
	private String settingsvalue;

	@Column(name = "settings_value", nullable = true)
	private String settingscode;

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

	@ManyToOne
	@JoinColumn(name = "company_id")
	private OrganisationCompany organisation;

	public OrganisationCompany getOrganisation() {
		return organisation;
	}

	public void setOrganisation(OrganisationCompany organisation) {
		this.organisation = organisation;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getSettingsvalue() {
		return settingsvalue;
	}

	public void setSettingsvalue(String settingsvalue) {
		this.settingsvalue = settingsvalue;
	}

	public String getSettingscode() {
		return settingscode;
	}

	public void setSettingscode(String settingscode) {
		this.settingscode = settingscode;
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

}