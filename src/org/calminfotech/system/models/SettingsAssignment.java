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

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(name = "spGetSettings", query = "{CALL sp_settings_assignment(:orgid)}", callable = true, resultClass = SettingsAssignment.class)
@Table(name = "sys_settings")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class SettingsAssignment implements java.io.Serializable {

	private static final long serialVersionUID = -9160881819374280018L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "settings_code")
	private String settings_code;

	@Column(name = "settings_value", nullable = true)
	private String settings_value;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private OrganisationCompany organisation;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSettings_code() {
		return settings_code;
	}

	public void setSettings_code(String settings_code) {
		this.settings_code = settings_code;
	}

	public String getSettings_value() {
		return settings_value;
	}

	public void setSettings_value(String settings_value) {
		this.settings_value = settings_value;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public OrganisationCompany getOrganisation() {
		return organisation;
	}

	public void setOrganisation(OrganisationCompany organisation) {
		this.organisation = organisation;
	}

}