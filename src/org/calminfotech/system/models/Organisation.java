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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.utils.models.Country;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.State;
import org.hibernate.annotations.SQLDelete;

//import java.sql.Date;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "organisations")
@SQLDelete(sql = "UPDATE organisations SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class Organisation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer Id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String Email;

	@Temporal(TemporalType.DATE)
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "established_year")
	private Date establishedYear;

	@Column(name = "phoneno")
	private String Phoneno;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private OrganisationCompany orgCoy;

	@Column(name = "is_deleted")
	private boolean isDelete = false;

	@ManyToOne
	@JoinColumn(name = "country_code")
	private Country country;

	@ManyToOne
	@JoinColumn(name = "state_code")
	private State state;

	@ManyToOne
	@JoinColumn(name = "lga_code")
	private LocalGovernmentArea lga;

	/**
	 * 
	 * Admin name and email should be on the user table
	 * 
	 */

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	@Column(name = "active")
	private boolean active;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;

	public Date getEstablishedYear() {
		return establishedYear;
	}

	public void setEstablishedYear(Date establishedYear) {
		this.establishedYear = establishedYear;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/*
	 * public Set<OrganisationDirector> getDirector() { return director; }
	 * 
	 * public void setDirector(Set<OrganisationDirector> director) {
	 * this.director = director; }
	 */

	public Integer getId() {
		return Id;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean isActive() {
		return active;
	}

	public String getPhoneno() {
		return Phoneno;
	}

	public void setPhoneno(String phoneno) {
		Phoneno = phoneno;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/*
	 * public Set<User> getUsers() { return users; }
	 * 
	 * public void setUsers(Set<User> user) { this.users = user; }
	 */

	public State getState() {
		return state;
	}

	public OrganisationCompany getOrgCoy() {
		return orgCoy;
	}

	public void setOrgCoy(OrganisationCompany orgCoy) {
		this.orgCoy = orgCoy;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setState(State state) {
		this.state = state;
	}

	public LocalGovernmentArea getLga() {
		return lga;
	}

	public void setLga(LocalGovernmentArea lga) {
		this.lga = lga;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
