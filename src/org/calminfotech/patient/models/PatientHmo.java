package org.calminfotech.patient.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.hmo.models.HmoPackage;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.utils.models.Hmostatus;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_hmo")
public class PatientHmo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fromdat")
	private Date fromdat;

	@Temporal(TemporalType.DATE)
	@Column(name = "todat")
	private Date todat;

	@Column(name = "patienthmocode")
	private String patienthmocode;

	@ManyToOne
	@JoinColumn(name = "hmopackage_id")
	private HmoPackage hmoPackage;

	public HmoPackage getHmoPackage() {
		return hmoPackage;
	}

	public void setHmoPackage(HmoPackage hmoPackage) {
		this.hmoPackage = hmoPackage;
	}

	@ManyToOne
	@JoinColumn(name = "hmostatus_id")
	private Hmostatus hmostatus;
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

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

	public Date getFromdat() {
		return fromdat;
	}

	public void setFromdat(Date fromdat) {
		this.fromdat = fromdat;
	}

	public Date getTodat() {
		return todat;
	}

	public void setTodat(Date todat) {
		this.todat = todat;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Hmostatus getHmostatus() {
		return hmostatus;
	}

	public void setHmostatus(Hmostatus hmostatus) {
		this.hmostatus = hmostatus;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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

	public String getPatienthmocode() {
		return patienthmocode;
	}

	public void setPatienthmocode(String patienthmocode) {
		this.patienthmocode = patienthmocode;
	}

}