package org.calminfotech.billing.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Paymode;
import org.calminfotech.visitqueue.models.Visit;
import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.CascadeType;
//import org.calminfotech.views.models.VwItem;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bill_invoice_payment")
@SQLDelete(sql = "UPDATE bill_invoice_payment SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class BillInvoicePayment {
	// variable

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "paymodeid")
	private Paymode paymode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payer_id")
	private Patient payer;

	@ManyToOne
	@JoinColumn(name = "visitid")
	private Visit visit;

	@ManyToOne
	@JoinColumn(name = "billid")
	private BillInvoice billInv;

	@ManyToOne
	@JoinColumn(name = "paysummaryid")
	private BillInvoicePaymentSummary billpaymentsummary;

	@Column(name = "description")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effectivedate")
	private Date effectivedate;

	@Column(name = "amtpaid")
	private Double amtpaid;

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

	@ManyToOne
	@JoinColumn(name = "createuser")
	private User createuser;

	public User getCreateuser() {
		return createuser;
	}

	public void setCreateuser(User createuser) {
		this.createuser = createuser;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Paymode getPaymode() {
		return paymode;
	}

	public void setPaymode(Paymode paymode) {
		this.paymode = paymode;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectivedate() {
		return effectivedate;
	}

	public void setEffectivedate(Date effectivedate) {
		this.effectivedate = effectivedate;
	}

	public Double getAmtpaid() {
		return amtpaid;
	}

	public void setAmtpaid(Double amtpaid) {
		this.amtpaid = amtpaid;
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

	public BillInvoice getBillInv() {
		return billInv;
	}

	public void setBillInv(BillInvoice billInv) {
		this.billInv = billInv;
	}

	public Patient getPayer() {
		return payer;
	}

	public void setPayer(Patient payer) {
		this.payer = payer;
	}

	public BillInvoicePaymentSummary getBillpaymentsummary() {
		return billpaymentsummary;
	}

	public void setBillpaymentsummary(
			BillInvoicePaymentSummary billpaymentsummary) {
		this.billpaymentsummary = billpaymentsummary;
	}

}