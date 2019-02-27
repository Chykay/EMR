package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class OrganisationEditForm {
	private Integer id;

	@NotBlank(message = "Field cannot be empty!")
	private String name;
	private String description;

	@NotBlank(message = "Field cannot be empty!")
	private String address;

	@NotBlank(message = "Field cannot be empty!")
	@Email(message = "Invalid email format!")
	private String systemEmail;
	
	private String consultationCode;
	
	private String organisationType;
	
	private String establishedYear;	
	
	private String areaOfSpecialisation;
	
	private Integer stateId;
	
	private Integer lgaId;
	
	//Getters and Setters

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

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

	public String getConsultationCode() {
		return consultationCode;
	}

	public void setConsultationCode(String consultationCode) {
		this.consultationCode = consultationCode;
	}

	public String getOrganisationType() {
		return organisationType;
	}

	public void setOrganisationType(String organisationType) {
		this.organisationType = organisationType;
	}

	public String getEstablishedYear() {
		return establishedYear;
	}

	public void setEstablishedYear(String establishedYear) {
		this.establishedYear = establishedYear;
	}

	public String getAreaOfSpecialisation() {
		return areaOfSpecialisation;
	}

	public void setAreaOfSpecialisation(String areaOfSpecialisation) {
		this.areaOfSpecialisation = areaOfSpecialisation;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getLgaId() {
		return lgaId;
	}

	public void setLgaId(Integer lgaId) {
		this.lgaId = lgaId;
	}

}
