package org.calminfotech.billing.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import org.calminfotech.views.models.VwItem;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
// @Table(name = "billing_invoice")
// @SQLDelete(sql = "UPDATE billing_invoice SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class GetPatientHmo {
	// variable

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patienthmopackageid")
	private Integer patienthmopackageid;

	@Column(name = "hmoid")
	private Integer hmoid;

	@Column(name = "hmoname")
	private String hmoname;

	@Column(name = "hmopackageid")
	private Integer hmopackageid;

	@Column(name = "hpname")
	private String hpname;

	@Column(name = "patientid")
	private Integer patientid;

	@Column(name = "surname")
	private String surname;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "visitinvid")
	private Integer visitinvid;

	@Column(name = "code")
	private String code;

	@Column(name = "billschemeid")
	private Integer billschemeid;

	@Column(name = "billschemename")
	private String billschemename;

	@Temporal(TemporalType.DATE)
	@Column(name = "fromdat")
	private Date fromdat;

	@Temporal(TemporalType.DATE)
	@Column(name = "todat")
	private Date todat;

	public Integer getPatienthmopackageid() {
		return patienthmopackageid;
	}

	public void setPatienthmopackageid(Integer patienthmopackageid) {
		this.patienthmopackageid = patienthmopackageid;
	}

	public Integer getHmoid() {
		return hmoid;
	}

	public void setHmoid(Integer hmoid) {
		this.hmoid = hmoid;
	}

	public String getHmoname() {
		return hmoname;
	}

	public void setHmoname(String hmoname) {
		this.hmoname = hmoname;
	}

	public Integer getHmopackageid() {
		return hmopackageid;
	}

	public void setHmopackageid(Integer hmopackageid) {
		this.hmopackageid = hmopackageid;
	}

	public String getHpname() {
		return hpname;
	}

	public void setHpname(String hpname) {
		this.hpname = hpname;
	}

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public Integer getVisitinvid() {
		return visitinvid;
	}

	public void setVisitinvid(Integer visitinvid) {
		this.visitinvid = visitinvid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getBillschemeid() {
		return billschemeid;
	}

	public void setBillschemeid(Integer billschemeid) {
		this.billschemeid = billschemeid;
	}

	public String getBillschemename() {
		return billschemename;
	}

	public void setBillschemename(String billschemename) {
		this.billschemename = billschemename;
	}

	public Date getFromdat() {
		return fromdat;
	}

	public void setFromdat(Date fromdat) {
		this.fromdat = fromdat;
	}

	public Date getTodat() {
		return todat;
	}

	public void setTodat(Date todat) {
		this.todat = todat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
