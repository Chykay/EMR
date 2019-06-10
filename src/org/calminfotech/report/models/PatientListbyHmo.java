package org.calminfotech.report.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "vw_Report_PatientListbyHMO")
public class PatientListbyHmo {

	@Id
	@Column(name = "patient_id")
	private Integer Pid;

	@Column(name = "patient_code")
	private String patientCode;

	@Column(name = "patient_fileno")
	private String patientFileno;

	@Column(name = "surname")
	private String surname;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "other_names")
	private String othernames;

	@Column(name = "email")
	private String email;

	@Column(name = "hmoid")
	private Integer hmoid;

	@Column(name = "hmoname")
	private String hmoname;

	@Column(name = "company_id")
	private int companyId;

	@Column(name = "organisation_id")
	private int organisationId;

	@Temporal(TemporalType.DATE)
	@Column(name = "startdate")
	private Date startdate;

	public Integer getPid() {
		return Pid;
	}

	public void setPid(Integer pid) {
		Pid = pid;
	}

	public String getPatientCode() {
		return patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	public String getPatientFileno() {
		return patientFileno;
	}

	public void setPatientFileno(String patientFileno) {
		this.patientFileno = patientFileno;
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

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getHmoid() {
		return hmoid;
	}

	public void setHmoid(Integer hmoid) {
		this.hmoid = hmoid;
	}

	public String getHmoname() {
		return hmoname;
	}

	public void setHmoname(String hmoname) {
		this.hmoname = hmoname;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

}
