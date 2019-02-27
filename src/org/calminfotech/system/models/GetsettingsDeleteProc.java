package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
	name = "spGetSettingsdeleteProc",
	query = "{CALL sp_delete_settings_value(:orgid)}",
	callable = true,
	resultClass = GetsettingsDeleteProc.class
)
//@Table(name = "sys_settings")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class GetsettingsDeleteProc implements java.io.Serializable {
	
	private static final long serialVersionUID = -9160881819374280018L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	
	
	@Column(name = "settings_code",unique = false, nullable = true)
	private String settings_code;
	
	@Column(name = "name",unique = false, nullable = true)
	private String name;
	

	@Column(name = "searchtype",unique = false, nullable = true)
	private String searchtype;
	
	
	@Column(name = "settings_value", nullable = true)
	private Integer settings_value;


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



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSearchtype() {
		return searchtype;
	}



	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}



	public String getSettings_code() {
		return settings_code;
	}



	public void setSettings_code(String settings_code) {
		this.settings_code = settings_code;
	}



	public Integer getSettings_value() {
		return settings_value;
	}



	public void setSettings_value(Integer settings_value) {
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



	public Integer getOrganisation_id() {
		return organisation_id;
	}



	public void setOrganisation_id(Integer organisation_id) {
		this.organisation_id = organisation_id;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Column(name = "organisation_id",nullable = false)
	private Integer organisation_id;
	
	


}