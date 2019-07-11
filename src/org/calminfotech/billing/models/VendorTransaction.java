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

//import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Paymode;
import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.CascadeType;
//import org.calminfotech.views.models.VwItem;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Vendortransaction")
@SQLDelete(sql = "UPDATE Vendortransaction SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class VendorTransaction {
	// variable

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "tranrefno")
	private String tranrefno;

	@Column(name = "trantype")
	private String trantype;

	@Column(name = "drcr")
	private String drcr;

	@Column(name = "code")
	private String code;

	@Column(name = "created_date")
	private Date createdDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effectivedate")
	private Date effectivedate;

	@Column(name = "amount")
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "paymodeid")
	private Paymode paymode;

	// TODO i DON'T HAVE THIS
	/*@ManyToOne
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;*/

	/*
	 * @Transient private Map mfig;
	 * 
	 * public Map getMfig() {
	 * 
	 * // Double totamt=0.00; Double totpaymt = 0.00; // Double tothmo=0.00; //
	 * Double totcash=0.00;
	 * 
	 * Map<String, Double> mfig = new HashMap(); try { for (BillInvoicePayment
	 * pay : this.getBillinvoicepayment()) {
	 * 
	 * totpaymt = totpaymt + pay.getAmtpaid();
	 * 
	 * }
	 * 
	 * } catch (Exception e) { System.out
	 * .println("Problem in getting billpayment attached to invoice set"); }
	 * 
	 * // mfig.put("totamt",totamt); mfig.put("totpaymt", totpaymt); //
	 * mfig.put("totcash",totcash); // mfig.put("tothmo",tothmo);
	 * mfig.put("totbal", cashamt - totpaymt); return mfig; }
	 * 
	 * public void setMfig(Map mfig) { this.mfig = mfig; }
	 */

	public String getDrcr() {
		return drcr;
	}

	public void setDrcr(String drcr) {
		this.drcr = drcr;
	}

	public String getTranrefno() {
		return tranrefno;
	}

	public void setTranrefno(String tranrefno) {
		this.tranrefno = tranrefno;
	}

	public String getTrantype() {
		return trantype;
	}

	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}

	public Paymode getPaymode() {
		return paymode;
	}

	public void setPaymode(Paymode paymode) {
		this.paymode = paymode;
	}

	/*public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}*/

	@Column(name = "created_by")
	private String createdBy;

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

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectivedate() {
		return effectivedate;
	}

	public void setEffectivedate(Date effectivedate) {
		this.effectivedate = effectivedate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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

}