package org.calminfotech.report.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.system.models.Organisation;

/*** shade kunlett **/
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "vw_report_billing_list")
public class BillingListReport {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "patient_id")
	private Integer Pid;

	@Column(name = "globalitemid")
	private Integer globalitemid;

	@Column(name = "globalitem_name")
	private String globalitem_name;

	public Integer getGlobalitemid() {
		return globalitemid;
	}

	public void setGlobalitemid(Integer globalitemid) {
		this.globalitemid = globalitemid;
	}

	public String getGlobalitem_name() {
		return globalitem_name;
	}

	public void setGlobalitem_name(String globalitem_name) {
		this.globalitem_name = globalitem_name;
	}

	public Integer getPid() {
		return Pid;
	}

	public void setPid(Integer pid) {
		Pid = pid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "duedate")
	private Date duedate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdate;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@Column(name = "company_id")
	private Integer company_id;

	@Column(name = "qty")
	private Integer quantity;

	@Column(name = "created_by")
	private String billedby;

	@Column(name = "description")
	private String billedfor;

	@Column(name = "hmoid")
	private Integer hmoId;

	@Column(name = "hmopackageid")
	private Integer pkgId;

	@Column(name = "patient_fileno")
	private String patient_fileno;

	@Column(name = "code")
	private String code;

	@Column(name = "usernames")
	private String usernames;

	@Column(name = "hrunitcategory_id")
	private Integer hrunitcategory_id;

	@Column(name = "category_name")
	private String category_name;

	@Column(name = "hmoname")
	private String hmoname;

	@Column(name = "phone")
	private String hmophone;

	@Column(name = "hmopkg")
	private String hmopkgname;

	@Column(name = "surname")
	private String surname;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "other_names")
	private String othernames;

	@Column(name = "invamt")
	private Double invamt;

	@Column(name = "cashamt")
	private Double cashamt;

	@Column(name = "hmoamt")
	private Double hmoamt;

	@Column(name = "amtpaid")
	private Double amtpaid;

	@Column(name = "cashmode")
	private Double cashmode;

	@Column(name = "posmode")
	private Double posmode;

	@Column(name = "transfermode")
	private Double transfermode;

	@Column(name = "depositmode")
	private Double depositmode;

	public Double getCashmode() {
		return cashmode;
	}

	public void setCashmode(Double cashmode) {
		this.cashmode = cashmode;
	}

	public Double getPosmode() {
		return posmode;
	}

	public void setPosmode(Double posmode) {
		this.posmode = posmode;
	}

	public Double getTransfermode() {
		return transfermode;
	}

	public void setTransfermode(Double transfermode) {
		this.transfermode = transfermode;
	}

	public Double getDepositmode() {
		return depositmode;
	}

	public void setDepositmode(Double depositmode) {
		this.depositmode = depositmode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getHrunitcategory_id() {
		return hrunitcategory_id;
	}

	public void setHrunitcategory_id(Integer hrunitcategory_id) {
		this.hrunitcategory_id = hrunitcategory_id;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getBilledby() {
		return billedby;
	}

	public void setBilledby(String billedby) {
		this.billedby = billedby;
	}

	public String getBilledfor() {
		return billedfor;
	}

	public void setBilledfor(String billedfor) {
		this.billedfor = billedfor;
	}

	public Integer getHmoId() {
		return hmoId;
	}

	public void setHmoId(Integer hmoId) {
		this.hmoId = hmoId;
	}

	public Integer getPkgId() {
		return pkgId;
	}

	public void setPkgId(Integer pkgId) {
		this.pkgId = pkgId;
	}

	public String getHmoname() {
		return hmoname;
	}

	public void setHmoname(String hmoname) {
		this.hmoname = hmoname;
	}

	public String getHmophone() {
		return hmophone;
	}

	public void setHmophone(String hmophone) {
		this.hmophone = hmophone;
	}

	public String getHmopkgname() {
		return hmopkgname;
	}

	public void setHmopkgname(String hmopkgname) {
		this.hmopkgname = hmopkgname;
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

	public Double getAmtpaid() {
		return amtpaid;
	}

	public void setAmtpaid(Double amtpaid) {
		this.amtpaid = amtpaid;
	}

	public String getPatient_fileno() {
		return patient_fileno;
	}

	public void setPatient_fileno(String patient_fileno) {
		this.patient_fileno = patient_fileno;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getUsernames() {
		return usernames;
	}

	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}

}
