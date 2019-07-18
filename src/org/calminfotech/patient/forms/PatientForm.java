package org.calminfotech.patient.forms;

public class PatientForm {

	private Integer patientId;

	private String patientcode;

	private String patientfileno;

	private String patientbvn;

	private String patientnid;

	private String patientpid;

	// @NotBlank(message = "Field cannot be empty!")
	private String surname;

	// @NotBlank(message = "Field cannot be empty!")
	private String firstName;

	// @NotBlank(message = "Field cannot be empty!")
	private String othernames;

	// @NotBlank(message = "Field cannot be empty!")

	private String email;
	private Integer maritalstatusId;
	private Integer languageId;
	private Integer occupationId;
	private String dob;
	private String startdate;
	private String statusdate;
	private Integer genotypeId;
	private Integer bldgrpId;
	private Double height;
	private Double creditlimit;
	private Integer organisationId;
	private Integer lifestatusId;

	private String lgaCode;
	private String countryCode;
	private String stateCode;

	// private String address;

	// @Range(message = "Please select a LGA")
	private Integer lgaId;

	// @Range(message = "Please select a State")
	private Integer stateId;

	// @Range( message = "Select a title")
	private Integer titleId;

	public String getLgaCode() {
		return lgaCode;
	}

	public void setLgaCode(String lgaCode) {
		this.lgaCode = lgaCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getPatientbvn() {
		return patientbvn;
	}

	public void setPatientbvn(String patientbvn) {
		this.patientbvn = patientbvn;
	}

	public String getPatientnid() {
		return patientnid;
	}

	public void setPatientnid(String patientnid) {
		this.patientnid = patientnid;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getPatientpid() {
		return patientpid;
	}

	public void setPatientpid(String patientpid) {
		this.patientpid = patientpid;
	}

	public Integer getMaritalstatusId() {
		return maritalstatusId;
	}

	public void setMaritalstatusId(Integer maritalstatusId) {
		this.maritalstatusId = maritalstatusId;
	}

	// @Range(min = 1, message = "Select a gender type")
	private Integer genderId;

	// @Range(min = 1, message = "Select a gender type")

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getPatientcode() {
		return patientcode;
	}

	public void setPatientcode(String patientcode) {
		this.patientcode = patientcode;
	}

	public String getPatientfileno() {
		return patientfileno;
	}

	public void setPatientfileno(String patientfileno) {
		this.patientfileno = patientfileno;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getOthernames() {
		return othernames;
	}

	public void setOthernames(String othernames) {
		this.othernames = othernames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public Integer getGenotypeId() {
		return genotypeId;
	}

	public void setGenotypeId(Integer genotypeId) {
		this.genotypeId = genotypeId;
	}

	public Integer getBldgrpId() {
		return bldgrpId;
	}

	public void setBldgrpId(Integer bldgrpId) {
		this.bldgrpId = bldgrpId;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public String getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(String statusdate) {
		this.statusdate = statusdate;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Integer getLgaId() {
		return lgaId;
	}

	public void setLgaId(Integer lgaId) {
		this.lgaId = lgaId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Integer getLifestatusId() {
		return lifestatusId;
	}

	public void setLifestatusId(Integer lifestatusId) {
		this.lifestatusId = lifestatusId;
	}

	public Double getCreditlimit() {
		return creditlimit;
	}

	public void setCreditlimit(Double creditlimit) {
		this.creditlimit = creditlimit;
	}

}
