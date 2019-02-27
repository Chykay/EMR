package org.calminfotech.hrunit.models;

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

/*import org.calminfotech.billing.models.BillInvoice;
import org.calminfotech.surgery.models.SurgeryDocument;
import org.calminfotech.surgery.models.SurgeryPersonnel;
*/
import org.calminfotech.system.models.Gender;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.utils.models.MaritalStatus;
import org.calminfotech.utils.models.Staffstatus;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "staff_registration")
//@SQLDelete(sql = "UPDATE staff_registration SET is_deleted = 1 WHERE id = ?")
//@Where(clause = "is_deleted <> 1")
public class StaffRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "staff_code")
	private String staffCode;
	
	@Column(name = "first_name")
	private String firstName;
	
	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MaritalStatus getMaritalststaus() {
		return maritalststaus;
	}

	public void setMaritalststaus(MaritalStatus maritalststaus) {
		this.maritalststaus = maritalststaus;
	}

	@Column(name = "last_name")
	private String lastName;
	

	@Column(name = "other_name")
	private String otherName;
	
	
	@Column(name = "email")
	private String email;
	
	
	@Column(name = "practno")
	private String practno;
	
	

	@Column(name = "verifymode")
	private String verifymode;
	
	@Temporal(TemporalType.DATE)
	
	@Column(name = "dob")
	private Date dob;
	
	
	
	@Temporal(TemporalType.DATE)

	@Column(name = "doe")
	private Date doe;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "statusdate")
	private Date statusdate;
	
	
	public Date getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(Date statusdate) {
		this.statusdate = statusdate;
	}

	@Column(name = "address")
	private String address;
	
	@Column(name = "telephone")
	private String telephone;
	
	
	@Column(name = "qualifications")
	private String qualifications;
	
		
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "modify_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	

	@ManyToOne
	@JoinColumn(name = "staffstatus_id")
	private Staffstatus staffstatus;

	
	
	@ManyToOne
	@JoinColumn(name = "staffgroup_id")
	private Staffgroup staffgroup;
	
	
	@ManyToOne
	@JoinColumn(name = "staffranking_id")
	private Staffgroupranking staffranking;
	
	

	@ManyToOne
	@JoinColumn(name = "staffspecialization_id")
	private Staffgroupspecialization staffspecialization;
	
	
	@ManyToOne
	@JoinColumn(name = "staffmode_id")
	private Staffemploymentmode staffmode;
	
	
	@ManyToOne
	@JoinColumn(name = "staffunit_id")
	private HrunitCategory  hrunitcategory;
	
	
	

	@ManyToOne
	@JoinColumn(name = "maritalstatus_id")
	private MaritalStatus  maritalststaus;
	
	
	@ManyToOne
	@JoinColumn(name = "gender_id")
	private Gender  gender;
	
//	
//	@OneToMany (cascade=CascadeType.ALL)
//	@JoinColumn(name = "staff_id")
//	@Where(clause = "is_deleted <> 1")
//	@OrderBy("id DESC")
//	private Set<SurgeryPersonnel> surgerypersonnel; 
	

/*	@OneToMany (cascade=CascadeType.ALL)
	@JoinColumn(name = "staffid")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<BillInvoice> billinvoice; 
*/
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;
	
	
	
	
	
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
/*
	public Set<BillInvoice> getBillinvoice() {
		return billinvoice;
	}

	public void setBillinvoice(Set<BillInvoice> billinvoice) {
		this.billinvoice = billinvoice;
	}*/

//	public Set<SurgeryPersonnel> getSurgerypersonnel() {
//		return surgerypersonnel;
//	}
//
//	public void setSurgerypersonnel(Set<SurgeryPersonnel> surgerypersonnel) {
//		this.surgerypersonnel = surgerypersonnel;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoe() {
		return doe;
	}

	public void setDoe(Date doe) {
		this.doe = doe;
	}


	public Staffstatus getStaffstatus() {
		return staffstatus;
	}

	public void setStaffstatus(Staffstatus staffstatus) {
		this.staffstatus = staffstatus;
	}

	public Staffgroup getStaffgroup() {
		return staffgroup;
	}

	public void setStaffgroup(Staffgroup staffgroup) {
		this.staffgroup = staffgroup;
	}

	public Staffgroupranking getStaffranking() {
		return staffranking;
	}

	public void setStaffranking(Staffgroupranking staffranking) {
		this.staffranking = staffranking;
	}

	public Staffgroupspecialization getStaffspecialization() {
		return staffspecialization;
	}

	public void setStaffspecialization(Staffgroupspecialization staffspecialization) {
		this.staffspecialization = staffspecialization;
	}

	public Staffemploymentmode getStaffmode() {
		return staffmode;
	}

	public void setStaffmode(Staffemploymentmode staffmode) {
		this.staffmode = staffmode;
	}

	public HrunitCategory getHrunitcategory() {
		return hrunitcategory;
	}

	public void setHrunitcategory(HrunitCategory hrunitcategory) {
		this.hrunitcategory = hrunitcategory;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	

}
