package org.calminfotech.system.models;

import java.sql.Blob;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.user.models.Role;
import org.calminfotech.utils.models.Country;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.State;
import org.hibernate.annotations.SQLDelete;

//import java.sql.Date;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "organisations_company")
@SQLDelete(sql = "UPDATE organisationsCompany SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class OrganisationCompany {

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

	@ManyToOne
	@JoinColumn(name = "organisation_type_id")
	private OrganisationType organisationType;

	@ManyToOne
	@JoinColumn(name = "organisation_category_id")
	private OrganisationCategory organisationCategory;

	@Column(name = "email")
	private String Email;

	@Column(name = "domain")
	private String Domain;

	@Temporal(TemporalType.DATE)
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "established_year")
	private Date establishedYear;

	@Column(name = "phoneno")
	private String Phoneno;

	@Column(name = "logo")
	private Blob logo;

	@Column(name = "is_deleted")
	private boolean isDelete = false;

	@OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
	// @JoinColumn(name = "organisation_id")
	private Set<Role> role;

	@OneToMany(mappedBy = "orgCoy", cascade = CascadeType.ALL)
	// @JoinColumn(name = "organisation_id")
	private Set<Organisation> organisation;

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

	public Set<Organisation> getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Set<Organisation> organisation) {
		this.organisation = organisation;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	@Column(name = "active")
	private boolean active;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;

	@Column(name = "image_content_type", nullable = true)
	private String imageContentType;

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}

	public OrganisationType getOrganisationType() {
		return organisationType;
	}

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

	public void setOrganisationType(OrganisationType organisationType) {
		this.organisationType = organisationType;
	}

	public OrganisationCategory getOrganisationCategory() {
		return organisationCategory;
	}

	public void setOrganisationCategory(
			OrganisationCategory organisationCategory) {
		this.organisationCategory = organisationCategory;
	}

	/*
	 * public Set<OrganisationDirector> getDirector() { return director; }
	 * 
	 * public void setDirector(Set<OrganisationDirector> director) {
	 * this.director = director; }
	 */

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

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
		Id = id;
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

	public String getDomain() {
		return Domain;
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

	public void setDomain(String domain) {
		Domain = domain;
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
