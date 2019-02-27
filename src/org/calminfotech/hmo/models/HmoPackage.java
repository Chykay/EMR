package org.calminfotech.hmo.models;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/*
import org.calminfotech.billing.models.BillScheme;*/
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHmo;
import org.calminfotech.utils.models.Hmostatus;

@Entity
@Table(name = "hmo_package")

public class HmoPackage {
	
	@Id
	@GeneratedValue
	@Column(name ="id")
	private Integer id;
	
	
	@Column(name ="name")
	private String name;	
	
	
	
	@OneToMany
	@JoinColumn(name = "hmopackage_id")
	private Set<HmoPackageItem> hmopackageItem;
	
	
	@OneToMany
	@JoinColumn(name = "hmopackage_id")
	private Set<PatientHmo> patientHmo;
		
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Hmostatus hmostatus;
	
	
	public Hmostatus getHmostatus() {
		return hmostatus;
	}

	public void setHmostatus(Hmostatus hmostatus) {
		this.hmostatus = hmostatus;
	}

	@ManyToOne
	@JoinColumn(name = "hmo_id")
	private Hmo hmo;
	
	
	@Column(name ="organisation_id")
	private Integer organisationId;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	

	public Set<PatientHmo> getPatientHmo() {
		return patientHmo;
	}

	public void setPatientHmo(Set<PatientHmo> patientHmo) {
		this.patientHmo = patientHmo;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}



	public Hmo getHmo() {
		return hmo;
	}

	public void setHmo(Hmo hmo) {
		this.hmo = hmo;
	}


	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<HmoPackageItem> getHmopackageItem() {
		return hmopackageItem;
	}

	public void setHmopackageItem(Set<HmoPackageItem> hmopackageItem) {
		this.hmopackageItem = hmopackageItem;
	}

	public void setName(String name) {
		this.name = name;
	}


	

	
}

	
