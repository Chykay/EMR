package org.calminfotech.system.models;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "organisation_director")
public class OrganisationDirector {

	@Id
	@GeneratedValue
	@Column(name = "directorId")
	private Integer directorId;

	@Column(name = "director_lastName")
	private String directorLastName;

	@Column(name = "director_firstName")
	private String directorFirstName;

	@Column(name = "director_otherName")
	private String directorOtherName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "directorEmail")
	private String email;

	@Column(name = "directorPhone")
	private String directorPhone;

	@Column(name = "directorAvatar")
	private Blob directorAvatar;

	@Column(name = "avatar_contentType")
	private String contentType;

	@ManyToOne
	@JoinColumn(name = "organisationId")
	private OrganisationCompany organisation;

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

	// Getters and Setters

	public Integer getDirectorId() {
		return directorId;
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

	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
	}

	public String getDirectorLastName() {
		return directorLastName;
	}

	public void setDirectorLastName(String directorLastName) {
		this.directorLastName = directorLastName;
	}

	public String getDirectorFirstName() {
		return directorFirstName;
	}

	public void setDirectorFirstName(String directorFirstName) {
		this.directorFirstName = directorFirstName;
	}

	public String getDirectorOtherName() {
		return directorOtherName;
	}

	public void setDirectorOtherName(String directorOtherName) {
		this.directorOtherName = directorOtherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDirectorPhone() {
		return directorPhone;
	}

	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}

	public Blob getDirectorAvatar() {
		return directorAvatar;
	}

	public void setDirectorAvatar(Blob directorAvatar) {
		this.directorAvatar = directorAvatar;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public OrganisationCompany getOrganisation() {
		return organisation;
	}

	public void setOrganisation(OrganisationCompany organisation) {
		this.organisation = organisation;
	}

}
