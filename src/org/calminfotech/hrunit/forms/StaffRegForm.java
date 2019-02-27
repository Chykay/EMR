package org.calminfotech.hrunit.forms;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.Staffemploymentmode;
import org.calminfotech.hrunit.models.Staffgroup;
import org.calminfotech.hrunit.models.Staffgroupranking;
import org.calminfotech.hrunit.models.Staffgroupspecialization;
import org.calminfotech.utils.models.MaritalStatus;
import org.calminfotech.utils.models.Staffstatus;


public class StaffRegForm {
	
	private Integer id;

	private String staffCode;

	private String firstName;
	
	private String lastName;
	
	private String otherName;
	
	private String practno;
	
	private String verifymode;
	
	
	public String getPractno() {
		return practno;
	}

	public void setPractno(String practno) {
		this.practno = practno;
	}

	public String getVerifymode() {
		return verifymode;
	}

	public void setVerifymode(String verifymode) {
		this.verifymode = verifymode;
	}

	//@Pattern (regexp="^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$")
	private String email;
	
	private String statusdate;
	
	public String getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(String statusdate) {
		this.statusdate = statusdate;
	}

	private String dob;
	
	private String doe;
	
	
	private String address;
	
	private String qualifications;
	
	private String telephone;

	private Integer staffstatus_id;
	
	private Integer staffgroup_id;
	
	private Integer staffranking_id;
	
	private Integer staffspecialization_id;
	
	private Integer staffmode_id;
	
	private Integer staffunit_id;
	
	
	

	private Integer gender_id;

	
	
	private Integer maritalstatus_id;
	
	

	private String createdBy;

	private Date createDate;
	
	private String modifiedBy;

	private Date modifiedDate;
	
	private boolean isDeleted;
	
	private Integer organisation_id;
	
	
	

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	
	
	

	public Integer getGender_id() {
		return gender_id;
	}

	public void setGender_id(Integer gender_id) {
		this.gender_id = gender_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStaffCode() {
		return staffCode;
	}
	

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public Integer getStaffranking_id() {
		return staffranking_id;
	}

	public void setStaffranking_id(Integer staffranking_id) {
		this.staffranking_id = staffranking_id;
	}

	public Integer getStaffspecialization_id() {
		return staffspecialization_id;
	}

	public void setStaffspecialization_id(Integer staffspecialization_id) {
		this.staffspecialization_id = staffspecialization_id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	

	public Integer getStaffgroup_id() {
		return staffgroup_id;
	}

	public Integer getStaffmode_id() {
		return staffmode_id;
	}

	public Integer getStaffunit_id() {
		return staffunit_id;
	}

	public Integer getMaritalstatus_id() {
		return maritalstatus_id;
	}


	

	
	public Integer getStaffstatus_id() {
		return staffstatus_id;
	}

	public void setStaffstatus_id(Integer staffstatus_id) {
		this.staffstatus_id = staffstatus_id;
	}

	public void setStaffgroup_id(Integer staffgroup_id) {
		this.staffgroup_id = staffgroup_id;
	}


	public Integer getOrganisation_id() {
		return organisation_id;
	}

	public void setOrganisation_id(Integer organisation_id) {
		this.organisation_id = organisation_id;
	}

	public void setStaffmode_id(Integer staffmode_id) {
		this.staffmode_id = staffmode_id;
	}

	public void setStaffunit_id(Integer staffunit_id) {
		this.staffunit_id = staffunit_id;
	}

	

	public void setMaritalstatus_id(Integer maritalstatus_id) {
		this.maritalstatus_id = maritalstatus_id;
	}

	public String getDoe() {
		return doe;
	}

	public void setDoe(String doe) {
		this.doe = doe;
	}

}
