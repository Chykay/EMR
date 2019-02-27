package org.calminfotech.visitqueue.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

/*import org.calminfotech.billing.models.BillInvoice;
import org.calminfotech.billing.models.BillInvoicePayment;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasure;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.billing.models.BillInvoice;
import org.calminfotech.billing.models.BillInvoicePayment;*/
//import org.calminfotech.consultation.models.VisitConsultation;
//import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.user.models.User;
import org.hibernate.annotations.SQLDelete;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "visit_consultation_visitations")
@SQLDelete(sql = "UPDATE visit_consultation_visitations SET is_deleted = 1 WHERE id = ?")
//@Where(clause = "is_deleted <> 1")
public class Visit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "code")
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	/*
	public Set<VisitConsultation> getConsultation() {
		return visitConsultation;
	}

	public void setConsultation(Set<VisitConsultation> visitConsultation) {
		this.visitConsultation = visitConsultation;
	}
*/
	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effectivedate")
	private Date effectiveDate;

	

/*	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;



	@ManyToOne
	@JoinColumn(name = "globalitem_id")
	private GlobalItem globalitem;
		

	@ManyToOne
	@JoinColumn(name = "globalitemmeasure_id")
	private GlobalItemUnitofMeasure globalitemmeasure;
		
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	*/
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	

//	@OneToMany
//	@JoinColumn(name = "visit_id")
//	private Set<VisitConsultation> visitConsultation;
//
//

	/*@OneToMany
	@Where(clause = "is_deleted <> 1")
	@JoinColumn(name = "visitid")
	private List<BillInvoice> billinvoice = new ArrayList() ;

	


	@OneToMany
	@Where(clause = "is_deleted <> 1")
	@JoinColumn(name = "visitid")
	private List<BillInvoicePayment> billinvoicepayment = new ArrayList() ;


	
	@ManyToOne
	@JoinColumn(name = "billto")
	private Patient patientbillto;
	
	
	*/
	
	@ManyToOne
	@JoinColumn(name = "visit_status_id")
	private VisitStatus status;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "visit_id")
	@OrderBy("createDate ASC")
	private Set<VisitTimeline> timeline = new HashSet<VisitTimeline>();

	@ManyToOne
	@JoinColumn(name = "point_id")
	private VisitWorkflowPoint point;
	
	
	/*@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;*/
//	
//	@ManyToOne
//	@JoinColumn(name = "unit_id")
//	private HrunitCategory unit;
//	
//	
//	@ManyToOne
//	@JoinColumn(name = "current_unit_id")
//	private HrunitCategory currentunit;
//	
//	
	/*@ManyToOne
	@JoinColumn(name = "section_id")
	private LoginSection loginSection;*/
	
	
	/*private Integer clerking_status_id;*/
	

	@Transient
	private Map mfig;
	
	
	
	
	
	public Map getMfig() {
		

		Double totamt=0.00;
		Double totpaymt=0.00;
		Double tothmo=0.00;
		Double totcash=0.00;
		Map<String, Double> mfig = new HashMap();
		
		/*for (BillInvoice bill : this.getBillinvoice())
		{
			
		    totamt = totamt + bill.getInvamt();
			
			tothmo = tothmo + bill.getHmoamt();
			
			totcash =  totcash + bill.getCashamt();
			
		}
		
		

		for (BillInvoicePayment pay : this.getBillinvoicepayment())
		{

			totpaymt=totpaymt+ pay.getAmtpaid();
			
		}*/
		
		
		mfig.put("totamt",totamt);
		mfig.put("totpaymt",totpaymt);
		mfig.put("totcash",totcash);
		mfig.put("tothmo",tothmo);
		
		
		
		
		return mfig;
	}

		
	
	
	
	public void setMfig(Map mfig) {
		this.mfig = mfig;
	}

	public Integer getId() {
		return id;
	}

	
	/*public List<BillInvoicePayment> getBillinvoicepayment() {
		return billinvoicepayment;
	}

	public void setBillinvoicepayment(List<BillInvoicePayment> billinvoicepayment) {
		this.billinvoicepayment = billinvoicepayment;
	}

	public List<BillInvoice> getBillinvoice() {
		return billinvoice;
	}

	public void setBillinvoice(List<BillInvoice> billinvoice) {
		this.billinvoice = billinvoice;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public HrunitCategory getCurrentunit() {
//		return currentunit;
//	}
//
//	public void setCurrentunit(HrunitCategory currentunit) {
//		this.currentunit = currentunit;
//	}

	/*public GlobalItem getGlobalitem() {
		return globalitem;
	}

	public void setGlobalitem(GlobalItem globalitem) {
		this.globalitem = globalitem;
	}

	public GlobalItemUnitofMeasure getGlobalitemmeasure() {
		return globalitemmeasure;
	}

	public void setGlobalitemmeasure(GlobalItemUnitofMeasure globalitemmeasure) {
		this.globalitemmeasure = globalitemmeasure;
	}*/

//	public Set<VisitConsultation> getVisitConsultation() {
//		return visitConsultation;
//	}
//
//	public void setVisitConsultation(Set<VisitConsultation> visitConsultation) {
//		this.visitConsultation = visitConsultation;
//	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	


	/*public Patient getPatientbillto() {
		return patientbillto;
	}

	public void setPatientbillto(Patient patientbillto) {
		this.patientbillto = patientbillto;
	}*/

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public HrunitCategory getUnit() {
//		return unit;
//	}
//
//	public void setUnit(HrunitCategory unit) {
//		this.unit = unit;
//	}

	/*public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
*/
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public VisitStatus getStatus() {
		return status;
	}

	public void setStatus(VisitStatus status) {
		this.status = status;
	}

	/**
	 * @return the doctorVisit
	 */
	/**
	 * @return the pharmacyVisit

	/**
	 * @param pharmacyVisit
	 *            the pharmacyVisit to set
	 */


	/**
	 * @return the vitalVisit
	 */
	

	/**
	 * @param vitalVisit
	 *            the vitalVisit to set
	 */
	
	public VisitWorkflowPoint getPoint() {
		return point;
	}

	public void setPoint(VisitWorkflowPoint point) {
		this.point = point;
	}

	
	/**
	 * @param laboratoryVisit
	 *            the laboratoryVisit to set
	 */
	

	/**
	 * @return the timeline
	 */
	public Set<VisitTimeline> getTimeline() {
		return timeline;
	}

	/**
	 * @param timeline
	 *            the timeline to set
	 */
	public void setTimeline(Set<VisitTimeline> timeline) {
		this.timeline = timeline;
	}

	/**
	 * @return the labTest
	 */



	/*public Integer getClerking_status_id() {
		return clerking_status_id;
	}

	public void setClerking_status_id(Integer clerking_status_id) {
		this.clerking_status_id = clerking_status_id;
	}*/



	

	public Date getModifyDate() {
		return modifyDate;
	}

	

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
	
/*	public VisitClerkingStatus getvClerkingStatus() {
		return vClerkingStatus;
	}

	public void setvClerkingStatus(VisitClerkingStatus vClerkingStatus) {
		this.vClerkingStatus = vClerkingStatus;
	}*/

/*	public LoginSectionPoint getLoginSectionPoint() {
		return loginSectionPoint;
	}

	public void setLoginSectionPoint(LoginSectionPoint loginSectionPoint) {
		this.loginSectionPoint = loginSectionPoint;
	}*/


}
