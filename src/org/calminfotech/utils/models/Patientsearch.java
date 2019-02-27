package org.calminfotech.utils.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

//import org.calminfotech.system.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "vw_patient_search")
@SQLDelete(sql = "UPDATE patients SET is_deleted = 1 WHERE patient_id = ?")
// @Where(clause = "is_deleted <> 1")
public class Patientsearch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "patient_id", unique = true, nullable = false)
	private Integer patientId;

	@Column(name = "patient_code")
	private String patientCode;

	@Column(name = "patient_fileno")
	private String patientFileno;

	@Column(name = "surname")
	private String surname;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "other_names")
	private String otherName;

	@Column(name = "email")
	private String email;

	@Column(name = "telnumber")
	private String telnumber;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelnumber() {
		return telnumber;
	}

	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}

}
