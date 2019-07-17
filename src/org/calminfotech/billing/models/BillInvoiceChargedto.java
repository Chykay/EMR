package org.calminfotech.billing.models;

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

import org.calminfotech.hmo.models.HmoPackage;
import org.calminfotech.hmo.models.HmoPackageItem;
import org.calminfotech.patient.models.PatientHmo;
import org.calminfotech.system.models.Organisation;
import org.hibernate.annotations.SQLDelete;
//import org.calminfotech.views.models.VwItem;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bill_invoice_chargedto")
@SQLDelete(sql = "UPDATE bill_invoice_chargedto SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class BillInvoiceChargedto {
	// variable

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "bill_invoice_id")
	private BillInvoice billinvoice;

	@ManyToOne
	@JoinColumn(name = "patienthmopackageid")
	private PatientHmo patienthmopackage;

	@ManyToOne
	@JoinColumn(name = "hmopackageid")
	private HmoPackage hmopackage;

	@ManyToOne
	@JoinColumn(name = "hmopackageitemid")
	private HmoPackageItem hmopackageitem;

	@ManyToOne
	@JoinColumn(name = "billschemeid")
	private BillScheme billscheme;

	@Temporal(TemporalType.DATE)
	@Column(name = "duedate")
	private Date duedate;

	@Column(name = "invamt")
	private Double invamt;

	@Column(name = "cashamt")
	private Double cashamt;

	@Column(name = "amtpaid")
	private Double amtpaid;

	@Column(name = "hmoamt")
	private Double hmoamt;

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

	public HmoPackageItem getHmopackageitem() {
		return hmopackageitem;
	}

	public Double getAmtpaid() {
		return amtpaid;
	}

	public void setAmtpaid(Double amtpaid) {
		this.amtpaid = amtpaid;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public void setHmopackageitem(HmoPackageItem hmopackageitem) {
		this.hmopackageitem = hmopackageitem;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BillInvoice getBillinvoice() {
		return billinvoice;
	}

	public void setBillinvoice(BillInvoice billinvoice) {
		this.billinvoice = billinvoice;
	}

	public PatientHmo getPatienthmopackage() {
		return patienthmopackage;
	}

	public void setPatienthmopackage(PatientHmo patienthmopackage) {
		this.patienthmopackage = patienthmopackage;
	}

	public HmoPackage getHmopackage() {
		return hmopackage;
	}

	public void setHmopackage(HmoPackage hmopackage) {
		this.hmopackage = hmopackage;
	}

	public BillScheme getBillscheme() {
		return billscheme;
	}

	public void setBillscheme(BillScheme billscheme) {
		this.billscheme = billscheme;
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

}
