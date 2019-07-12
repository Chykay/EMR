package org.calminfotech.billing.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.persistence.Transient;

import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.hmo.models.HmoPackage;
import org.calminfotech.hmo.models.HmoPackageItem;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.StaffRegistration;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHmo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.User;
// import org.calminfotech.visitqueue.models.VisitInvoice;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
//import org.hibernate.annotations.CascadeType;
//import org.calminfotech.views.models.VwItem;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bill_invoice")
@SQLDelete(sql = "UPDATE bill_invoice SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class BillInvoice {
	// variable

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "patienthmopackageid")
	private PatientHmo patienthmopackage;

	@ManyToOne
	@JoinColumn(name = "hmoid")
	private Hmo hmo;

	@ManyToOne
	@JoinColumn(name = "hmopackageid")
	private HmoPackage hmopackage;

	@ManyToOne
	@JoinColumn(name = "hmopackageitemid")
	private HmoPackageItem hmopackageitem;

	@ManyToOne
	@JoinColumn(name = "globalitemid")
	private GlobalItem globalitem;

	@ManyToOne
	@JoinColumn(name = "staffid")
	private StaffRegistration staffreg;

	@ManyToOne
	@JoinColumn(name = "unitid")
	private HrunitCategory unit;

	@ManyToOne
	@JoinColumn(name = "itemmeasureid")
	private GlobalItemUnitofMeasureVw itemmeasure;

	@Column(name = "qty")
	private Integer qty;

	@ManyToOne
	@JoinColumn(name = "patientid")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "createuser")
	private User createuser;

	public User getCreateuser() {
		return createuser;
	}

	public void setCreateuser(User createuser) {
		this.createuser = createuser;
	}

	public StaffRegistration getStaffreg() {
		return staffreg;
	}

	public void setStaffreg(StaffRegistration staffreg) {
		this.staffreg = staffreg;
	}

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "billownerpatientid") private Patient
	 * billownerpatient;
	 */
/*	@ManyToOne
	@JoinColumn(name = "visitinvid")
	private VisitInvoice visitinv;

	public VisitInvoice getVisitinv() {
		return visitinv;
	}

	public void setVisitinv(VisitInvoice visitinv) {
		this.visitinv = visitinv;
	}*/

	@Column(name = "code")
	private String code;

	@Column(name = "referenceid")
	private Integer referenceid;

	@Column(name = "description")
	private String description;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "bill_invoice_id")
	private Set<BillInvoiceChargedto> invoicecharged;

	@OneToMany
	@Where(clause = "is_deleted <> 1")
	@JoinColumn(name = "billid")
	private List<BillInvoicePayment> billinvoicepayment = new ArrayList();

	@ManyToOne
	@JoinColumn(name = "billschemeid")
	private BillScheme billscheme;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "duedate")
	private Date duedate;

	@Column(name = "invamt")
	private Double invamt;

	@Column(name = "netamt")
	private Double netamt;

	@Column(name = "cashamt")
	private Double cashamt;

	@Column(name = "vatamt")
	private Double vatamt;

	@Column(name = "hmoamt")
	private Double hmoamt;

	@Column(name = "groamt")
	private Double groamt;

	@Column(name = "addamt")
	private Double addamt;

	@Column(name = "dedamt")
	private Double dedamt;

	@Column(name = "amtpaid")
	private Double amtpaid;

	@Transient
	private Map mfig;

	public Map getMfig() {

		// Double totamt=0.00;
		Double totpaymt = 0.00;
		Double totcashmode = 0.00;
		Double totposmode = 0.00;
		Double tottransfermode = 0.00;
		Double totaccountmode = 0.00;

		Map<String, Double> mfig = new HashMap<String, Double>();
		try {
			for (BillInvoicePayment pay : this.getBillinvoicepayment()) {

				try {
					totpaymt = totpaymt + pay.getAmtpaid();

				} catch (Exception e) {
					System.out
							.println("Problem in getting billpayment attached to invoice set");
				}

				if (pay.getPaymode().getPaymode_id().intValue() == 1) {

					try {
						totcashmode = totcashmode + pay.getAmtpaid();

					} catch (Exception e) {
						System.out
								.println("Problem in getting billpayment attached to invoice set");
					}

				}

				if (pay.getPaymode().getPaymode_id().intValue() == 2) {

					try {
						totposmode = totposmode + pay.getAmtpaid();

					} catch (Exception e) {
						System.out
								.println("Problem in getting billpayment attached to invoice set");
					}

				}

				if (pay.getPaymode().getPaymode_id().intValue() == 3) {

					try {
						tottransfermode = tottransfermode + pay.getAmtpaid();

					} catch (Exception e) {
						System.out
								.println("Problem in getting billpayment attached to invoice set");
					}

				}

				if (pay.getPaymode().getPaymode_id().intValue() == 4) {

					try {
						totaccountmode = totaccountmode + pay.getAmtpaid();

					} catch (Exception e) {
						System.out
								.println("Problem in getting billpayment attached to invoice set");
					}

				}

			}

		} catch (Exception e) {
			System.out
					.println("Problem in getting billpayment attached to invoice set");
		}

		// mfig.put("totamt",totamt);
		mfig.put("totbal", this.cashamt - totpaymt);
		mfig.put("totpaymt", totpaymt);
		mfig.put("totcashmode", totcashmode);
		mfig.put("totposmode", totposmode);
		mfig.put("tottransfermode", tottransfermode);
		mfig.put("totaccountmode", totaccountmode);

		return mfig;
	}

	public void setMfig(Map mfig) {
		this.mfig = mfig;
	}

	public Double getAmtpaid() {
		return amtpaid;
	}

	public List<BillInvoicePayment> getBillinvoicepayment() {
		return billinvoicepayment;
	}

	public void setBillinvoicepayment(
			List<BillInvoicePayment> billinvoicepayment) {
		this.billinvoicepayment = billinvoicepayment;
	}

	public Double getNetamt() {
		return netamt;
	}

	public void setNetamt(Double netamt) {
		this.netamt = netamt;
	}

	public Double getVatamt() {
		return vatamt;
	}

	public void setVatamt(Double vatamt) {
		this.vatamt = vatamt;
	}

	public void setAmtpaid(Double amtpaid) {
		this.amtpaid = amtpaid;
	}

	public HmoPackageItem getHmopackageitem() {
		return hmopackageitem;
	}

	public void setHmopackageitem(HmoPackageItem hmopackageitem) {
		this.hmopackageitem = hmopackageitem;
	}

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Integer getId() {
		return id;
	}

	public Double getGroamt() {
		return groamt;
	}

	public Set<BillInvoiceChargedto> getInvoicecharged() {
		return invoicecharged;
	}

	public void setInvoicecharged(Set<BillInvoiceChargedto> invoicecharged) {
		this.invoicecharged = invoicecharged;
	}

	public void setGroamt(Double groamt) {
		this.groamt = groamt;
	}

	public Double getAddamt() {
		return addamt;
	}

	public void setAddamt(Double addamt) {
		this.addamt = addamt;
	}

	public Double getDedamt() {
		return dedamt;
	}

	public void setDedamt(Double dedamt) {
		this.dedamt = dedamt;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PatientHmo getPatienthmopackage() {
		return patienthmopackage;
	}

	public void setPatienthmopackage(PatientHmo patienthmopackage) {
		this.patienthmopackage = patienthmopackage;
	}

	public Hmo getHmo() {
		return hmo;
	}

	public void setHmo(Hmo hmo) {
		this.hmo = hmo;
	}

	public HmoPackage getHmopackage() {
		return hmopackage;
	}

	public void setHmopackage(HmoPackage hmopackage) {
		this.hmopackage = hmopackage;
	}

	public GlobalItem getGlobalitem() {
		return globalitem;
	}

	public void setGlobalitem(GlobalItem globalitem) {
		this.globalitem = globalitem;
	}

	public HrunitCategory getUnit() {
		return unit;
	}

	public void setUnit(HrunitCategory unit) {
		this.unit = unit;
	}

	public GlobalItemUnitofMeasureVw getItemmeasure() {
		return itemmeasure;
	}

	public void setItemmeasure(GlobalItemUnitofMeasureVw itemmeasure) {
		this.itemmeasure = itemmeasure;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getReferenceid() {
		return referenceid;
	}

	public void setReferenceid(Integer integer) {
		this.referenceid = integer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BillScheme getBillscheme() {
		return billscheme;
	}

	public void setBillscheme(BillScheme billscheme) {
		this.billscheme = billscheme;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public Double getInvamt() {
		return invamt;
	}

	public void setInvamt(Double invamt) {
		this.invamt = invamt;
	}

	public Double getCashamt() {
		return cashamt;
	}

	public void setCashamt(Double cashamt) {
		this.cashamt = cashamt;
	}

	public Double getHmoamt() {
		return hmoamt;
	}

	public void setHmoamt(Double hmoamt) {
		this.hmoamt = hmoamt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setVisitinv(Object visitationInvoiceById) {
		// TODO Auto-generated method stub
		
	}

}