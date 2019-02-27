package org.calminfotech.patient.models;

import java.sql.Blob;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Gender;
import org.calminfotech.system.models.Title;
import org.calminfotech.utils.models.Language;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.MaritalStatus;
import org.calminfotech.utils.models.State;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_casualty")
@SQLDelete(sql = "UPDATE patient_casualty SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class CasPatient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "patient_id")
	private Integer patient_id;

	@Column(name = "surname")
	private String surname;

	@Column(name = "othernames")
	private String othernames;

	@Column(name = "dob")
	private String dob;

	@Column(name = "email")
	private String email;

	@Column(name = "homeNumber")
	private String homeNumber;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;
	
	@Column(name = "genotype")
	private String genotype;
	
	@Column(name = "bldgrp")
	private String bldgrp;
	
	@Column(name = "height")
	private String height;
	
	
	@Column(name = "created_date")
	private Date createdDate;
	

	@Column(name = "active")
	private boolean active;


	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	@ManyToOne
	@JoinColumn(name = "gender_id")
	private Gender gender;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@ManyToOne
	@JoinColumn(name = "title_id")
	private Title title;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private MaritalStatus maritalStatus;
	
	@ManyToOne
	@JoinColumn(name = "lga_id")
	private LocalGovernmentArea lga;
		
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getOthernames() {
		return othernames;
	}

	public void setOthernames(String othernames) {
		this.othernames = othernames;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGenotype() {
		return genotype;
	}

	public void setGenotype(String genotype) {
		this.genotype = genotype;
	}

	public String getBldgrp() {
		return bldgrp;
	}

	public void setBldgrp(String bldgrp) {
		this.bldgrp = bldgrp;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public LocalGovernmentArea getLga() {
		return lga;
	}

	public void setLga(LocalGovernmentArea lga) {
		this.lga = lga;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	
	
	
	

}
