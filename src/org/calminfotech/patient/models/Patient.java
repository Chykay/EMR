package org.calminfotech.patient.models;

import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.calminfotech.billing.models.CustomerTransaction;
import org.calminfotech.system.models.Gender;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.Title;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Bloodgroup;
import org.calminfotech.utils.models.Country;
import org.calminfotech.utils.models.Language;
import org.calminfotech.utils.models.Lifestatus;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.MaritalStatus;
import org.calminfotech.utils.models.Occupation;
import org.calminfotech.utils.models.State;
import org.calminfotech.visitqueue.models.Visit;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

//import org.calminfotech.system.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_profile")
@SQLDelete(sql = "UPDATE patients SET is_deleted = 1 WHERE patient_id = ?")
// @Where(clause = "is_deleted <> 1")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id", unique = true, nullable = false)
	private Integer patientId;

	@Transient
	private String telnumber;

	public String getTelnumber() {
		return telnumber;
	}

	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}

	@Column(name = "patient_code")
	private String patientCode;

	@Column(name = "patient_fileno")
	private String patientFileno;

	@Column(name = "patient_nid")
	private String patientNid;

	@Column(name = "patient_bvn")
	private String patientBvn;

	@Column(name = "patient_pid")
	private String patientPid;

	@Column(name = "surname")
	private String surname;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "other_names")
	private String othernames;

	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	private Date dob;

	@Temporal(TemporalType.DATE)
	@Column(name = "startdate")
	private Date startdate;

	@Temporal(TemporalType.DATE)
	@Column(name = "statusdate")
	private Date statusdate;

	@Column(name = "email")
	private String email;

	/*
	 * @Column(name = "phone_number") private String phoneNumber;
	 * 
	 * @Column(name = "address") private String address;
	 */

	@Column(name = "height", nullable = true)
	private Double height;

	@Column(name = "creditlimit", nullable = true)
	private Double creditlimit;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private OrganisationCompany orgCoy;

	public OrganisationCompany getOrgCoy() {
		return orgCoy;
	}

	public void setOrgCoy(OrganisationCompany orgCoy) {
		this.orgCoy = orgCoy;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Double getCreditlimit() {
		return creditlimit;
	}

	public void setCreditlimit(Double creditlimit) {
		this.creditlimit = creditlimit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;

	@ManyToOne
	@JoinColumn(name = "lga_code")
	private LocalGovernmentArea lga;

	@ManyToOne
	@JoinColumn(name = "state_code")
	private State state;

	@ManyToOne
	@JoinColumn(name = "country_code")
	private Country country;

	@Column(name = "patient_image", nullable = true)
	private Blob image;

	@Column(name = "image_content_type", nullable = true)
	private String imageContentType;

	@ManyToOne
	@JoinColumn(name = "gender_id")
	private Gender gender;

	@ManyToOne
	@JoinColumn(name = "Bloodgroup_id")
	private Bloodgroup bloodgroup;

	@ManyToOne
	@JoinColumn(name = "Bloodgenotype_id")
	private Bloodgenotype bloodgenotype;

	@ManyToOne
	@JoinColumn(name = "title_id")
	private Title title;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	@ManyToOne
	@JoinColumn(name = "maritalstatus_id")
	private MaritalStatus maritalStatus;

	@ManyToOne
	@JoinColumn(name = "lifestatus_id")
	private Lifestatus lifestatus;

	@ManyToOne
	@JoinColumn(name = "occupation_id")
	private Occupation occupation;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<PatientDocument> patientDocuments = new HashSet<PatientDocument>();

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<CustomerTransaction> customertransaction = new HashSet<CustomerTransaction>();

	@OneToMany
	@JoinColumn(name = "patient_id")
	private Set<Visit> visit = new HashSet<Visit>();

	@OneToMany
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<PatientTelephone> patientTelephone;

	@OneToMany
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<PatientAddress> patientAddress;

	@OneToMany
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<PatientHistory> patientHistory = new HashSet<PatientHistory>();

	@OneToMany
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<PatientFamilyHistory> patientFamilyHistory = new HashSet<PatientFamilyHistory>();

	@OneToMany
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<PatientNok> patientNok;

	@OneToMany
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<PatientAllergy> patientAllergy = new HashSet<PatientAllergy>();

	@OneToMany
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<PatientRelated> patientRelated = new HashSet<PatientRelated>();

	@OneToMany
	@JoinColumn(name = "patient_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<PatientHmo> patientHmo;

	@Transient
	private Map mfig;

	public Map getMfig() {

		Double totamt = 0.00;
		Double totpaymt = 0.00;
		Double tothmo = 0.00;
		Double totcash = 0.00;
		Double totbal = 0.00;
		Double totcashmode = 0.00;
		Double totposmode = 0.00;
		Double tottransfermode = 0.00;
		Double totaccountmode = 0.00;
		Double totdebit = 0.00;
		Double totcredit = 0.00;
		Double totcustbal = 0.00;

		Map<String, Double> mfig = new HashMap<String, Double>();

		try {
			for (Visit vst : this.getVisit()) {
				try {
					totamt = totamt + (Double) vst.getMfig().get("totamt");

				} catch (Exception e) {
					System.out
							.println("Problem in getting billpayment attached to invoice set");
				}
				try {
					tothmo = tothmo + (Double) vst.getMfig().get("tothmo");

				} catch (Exception e) {
					System.out
							.println("Problem in getting billpayment attached to invoice set");
				}
				try {
					totcash = totcash + (Double) vst.getMfig().get("totcash");

				} catch (Exception e) {
					System.out
							.println("Problem in getting billpayment attached to invoice set");
				}

				try {
					totpaymt = totpaymt
							+ (Double) vst.getMfig().get("totpaymt");

				} catch (Exception e) {
					System.out.println("Problem in getting billinvoice set");
				}

				try {
					totbal = totbal + (Double) vst.getMfig().get("totbal");

				} catch (Exception e) {
					System.out.println("Problem in getting billinvoice set");
				}

			}

		} catch (Exception e) {
			System.out.println("Problem in getting billinvoice set");
		}

		try {
			for (CustomerTransaction pay : this.getCustomertransaction()) {
				try {
					totcustbal = totcustbal + pay.getAmount();

				} catch (Exception e) {
					System.out
							.println("Problem in getting billpayment attached to invoice set");
				}

				if (pay.getAmount().doubleValue() > 0
						&& pay.getDrcr().equalsIgnoreCase("Dr")) {
					try {
						totdebit = totdebit + pay.getAmount();

					} catch (Exception e) {
						System.out
								.println("Problem in getting customer debit to invoice set");
					}
				}

				if (pay.getAmount().doubleValue() < 0
						&& pay.getDrcr().equalsIgnoreCase("Cr")) {
					try {
						totcredit = totcredit + pay.getAmount();

					} catch (Exception e) {
						System.out
								.println("Problem in getting customer credit to invoice set");
					}

				}

			}

		} catch (Exception e) {
			System.out
					.println("Problem in getting customertransaction bal attached to invoice set");
		}

		mfig.put("totamt", totamt);
		mfig.put("totpaymt", totpaymt);
		mfig.put("totcash", totcash);
		mfig.put("tothmo", tothmo);
		mfig.put("totbal", totbal);
		mfig.put("totdebit", totdebit);
		mfig.put("totcredit", totcredit);

		mfig.put("totcustbal", totcustbal);
		mfig.put("totcustavailablebal", totcustbal - totbal);

		System.out.print("mifgavailable" + mfig.get("totcustavailablebal"));

		System.out.print("totcash" + totcash);
		System.out.print("totpayment" + totpaymt);

		System.out.print("totbal" + totbal);

		return mfig;

	}

	public void setMfig(Map mfig) {
		this.mfig = mfig;
	}

	public String getPatientNid() {
		return patientNid;
	}

	public void setPatientNid(String patientNid) {
		this.patientNid = patientNid;
	}

	public String getPatientBvn() {
		return patientBvn;
	}

	public void setPatientBvn(String patientBvn) {
		this.patientBvn = patientBvn;
	}

	public String getPatientPid() {
		return patientPid;
	}

	public void setPatientPid(String patientPid) {
		this.patientPid = patientPid;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<PatientHmo> getPatientHmo() {
		return patientHmo;
	}

	public void setPatientHmo(Set<PatientHmo> patientHmo) {
		this.patientHmo = patientHmo;
	}

	public Set<PatientRelated> getPatientRelated() {
		return patientRelated;
	}

	public void setPatientRelated(Set<PatientRelated> patientRelated) {
		this.patientRelated = patientRelated;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public Bloodgroup getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(Bloodgroup bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public Bloodgenotype getBloodgenotype() {
		return bloodgenotype;
	}

	public void setBloodgenotype(Bloodgenotype bloodgenotype) {
		this.bloodgenotype = bloodgenotype;
	}

	public Lifestatus getLifestatus() {
		return lifestatus;
	}

	public void setLifestatus(Lifestatus lifestatus) {
		this.lifestatus = lifestatus;
	}

	/**
	 * @return the patientAllergy
	 */

	public Set<PatientNok> getPatientNok() {
		return patientNok;
	}

	public Set<PatientAllergy> getPatientAllergy() {
		return patientAllergy;
	}

	public void setPatientAllergy(Set<PatientAllergy> patientAllergy) {
		this.patientAllergy = patientAllergy;
	}

	public void setPatientNok(Set<PatientNok> patientNok) {
		this.patientNok = patientNok;
	}

	public Set<PatientAddress> getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(Set<PatientAddress> patientAddress) {
		this.patientAddress = patientAddress;
	}

	public Set<PatientTelephone> getPatientTelephone() {
		return patientTelephone;
	}

	public void setPatientTelephone(Set<PatientTelephone> patientTelephone) {
		this.patientTelephone = patientTelephone;
	}

	public String getPatientFileno() {
		return patientFileno;
	}

	public void setPatientFileno(String patientFileno) {
		this.patientFileno = patientFileno;
	}

	public String getPatientCode() {
		return patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
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

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String string) {
		this.createdBy = string;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public Set<PatientDocument> getPatientDocuments() {
		return patientDocuments;
	}

	public void setPatientDocument(Set<PatientDocument> patientDocuments) {
		this.patientDocuments = patientDocuments;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Set<PatientHistory> getPatientHistory() {
		return patientHistory;
	}

	public void setPatientHistory(Set<PatientHistory> patientHistory) {
		this.patientHistory = patientHistory;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Set<PatientFamilyHistory> getPatientFamilyHistory() {
		return patientFamilyHistory;
	}

	public void setPatientFamilyHistory(
			Set<PatientFamilyHistory> patientFamilyHistory) {
		this.patientFamilyHistory = patientFamilyHistory;
	}

	/**
	 * @return the allergies
	 */

	public void setPatientDocuments(Set<PatientDocument> patientDocuments) {
		this.patientDocuments = patientDocuments;
	}

	/*
	 * public Set<PatientPaymentOption> getPatientpaymentoption() { return
	 * patientpaymentoption; }
	 * 
	 * public void setPatientpaymentoption( Set<PatientPaymentOption>
	 * patientpaymentoption) { this.patientpaymentoption = patientpaymentoption;
	 * }
	 */

	public Set<Visit> getVisit() {
		return visit;
	}

	public Set<CustomerTransaction> getCustomertransaction() {
		return customertransaction;
	}

	public void setCustomertransaction(
			Set<CustomerTransaction> customertransaction) {
		this.customertransaction = customertransaction;
	}

	public Date getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(Date statusdate) {
		this.statusdate = statusdate;
	}

	public void setVisit(Set<Visit> visit) {
		this.visit = visit;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
