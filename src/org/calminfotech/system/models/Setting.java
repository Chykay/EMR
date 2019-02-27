package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "settings")
public class Setting {

	private Integer id;
	private String organisationName;
	private String organisationAddress;
	private String organisationEmail;
	private String systemEmail;

	private boolean active;
	private Date createdDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "organisation_name", nullable = false)
	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	@Column(name = "organisation_address", nullable = false)
	public String getOrganisationAddress() {
		return organisationAddress;
	}

	public void setOrganisationAddress(String organisationAddress) {
		this.organisationAddress = organisationAddress;
	}

	@Column(name = "organisation_email", nullable = false)
	public String getOrganisationEmail() {
		return organisationEmail;
	}

	public void setOrganisationEmail(String organisationEmail) {
		this.organisationEmail = organisationEmail;
	}

	@Column(name = "system_email", nullable = false)
	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "created_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
