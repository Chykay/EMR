package org.calminfotech.billing.forms;

import java.util.Date;

/**
 * @author adeoye
 * 
 */
public class InvoiceBillForm {

	private Integer id;

	private Integer globalitemid;

	private String globalitemname;

	private Integer itemmeasureid;

	private String itemmeasurename;

	private Integer staffid;

	private Boolean usehmo;

	private Integer unitid;

	private Integer qty;

	private Integer patienthmopackageid;

	private Integer hmoid;

	private String hmoname;

	private Integer hmopackageid;

	private String hpname;

	private Integer patientid;

	private Integer billownerpatientid;

	private String surname;

	private String first_name;

	private Integer visitid;

	private Integer visitinvid;

	private String code;

	private Integer referenceid;

	private String description;

	private Integer billschemeid;

	private Date fromdat;

	private Date todat;

	private Double invamt;

	private Double cashamt;

	private double grandamt;

	private double vatamt;

	private double addamt;

	private double dedamt;

	private double netamt;

	private double groamt;

	private Double hmoamt;

	private Double vat;

	private Integer orgid;

	private String duedate;

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public double getGrandamt() {
		return grandamt;
	}

	public void setGrandamt(double grandamt) {
		this.grandamt = grandamt;
	}

	public Integer getVisitinvid() {
		return visitinvid;
	}

	public void setVisitinvid(Integer visitinvid) {
		this.visitinvid = visitinvid;
	}

	public Boolean getUsehmo() {
		return usehmo;
	}

	public void setUsehmo(Boolean usehmo) {
		this.usehmo = usehmo;
	}

	public double getVatamt() {
		return vatamt;
	}

	public void setVatamt(double vatamt) {
		this.vatamt = vatamt;
	}

	public Integer getBillownerpatientid() {
		return billownerpatientid;
	}

	public void setBillownerpatientid(Integer billownerpatientid) {
		this.billownerpatientid = billownerpatientid;
	}

	public Integer getStaffid() {
		return staffid;
	}

	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGlobalitemname() {
		return globalitemname;
	}

	public void setGlobalitemname(String globalitemname) {
		this.globalitemname = globalitemname;
	}

	public Integer getGlobalitemid() {
		return globalitemid;
	}

	public void setGlobalitemid(Integer globalitemid) {
		this.globalitemid = globalitemid;
	}

	public Integer getItemmeasureid() {
		return itemmeasureid;
	}

	public void setItemmeasureid(Integer itemmeasureid) {
		this.itemmeasureid = itemmeasureid;
	}

	public Integer getUnitid() {
		return unitid;
	}

	public void setUnitid(Integer unitid) {
		this.unitid = unitid;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getPatienthmopackageid() {
		return patienthmopackageid;
	}

	public void setPatienthmopackageid(Integer patienthmopackageid) {
		this.patienthmopackageid = patienthmopackageid;
	}

	public Integer getHmoid() {
		return hmoid;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
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

	public Integer getVisitid() {
		return visitid;
	}

	public void setVisitid(Integer visitid) {
		this.visitid = visitid;
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

	public void setReferenceid(Integer referenceid) {
		this.referenceid = referenceid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBillschemeid() {
		return billschemeid;
	}

	public void setBillschemeid(Integer billschemeid) {
		this.billschemeid = billschemeid;
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

	public String getItemmeasurename() {
		return itemmeasurename;
	}

	public void setItemmeasurename(String string) {
		this.itemmeasurename = string;
	}

	public double getAddamt() {
		return addamt;
	}

	public void setAddamt(double addamt) {
		this.addamt = addamt;
	}

	public double getDedamt() {
		return dedamt;
	}

	public void setDedamt(double dedamt) {
		this.dedamt = dedamt;
	}

	public double getGroamt() {
		return groamt;
	}

	public void setGroamt(double groamt) {
		this.groamt = groamt;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	@Override
	public String toString() {
		return "InvoiceBillForm [id=" + id + ", globalitemid=" + globalitemid
				+ ", globalitemname=" + globalitemname + ", itemmeasureid="
				+ itemmeasureid + ", itemmeasurename=" + itemmeasurename
				+ ", unitid=" + unitid + ", qty=" + qty
				+ ", patienthmopackageid=" + patienthmopackageid + ", hmoid="
				+ hmoid + ", hmoname=" + hmoname + ", hmopackageid="
				+ hmopackageid + ", hpname=" + hpname + ", patientid="
				+ patientid + ", surname=" + surname + ", first_name="
				+ first_name + ", visitid=" + visitid + ", code=" + code
				+ ", referenceid=" + referenceid + ", description="
				+ description + ", billschemeid=" + billschemeid + ", fromdat="
				+ fromdat + ", todat=" + todat + ", invamt=" + invamt
				+ ", cashamt=" + cashamt + ", addamt=" + addamt + ", dedamt="
				+ dedamt + ", groamt=" + groamt + ", hmoamt=" + hmoamt + "]";
	}

	public double getNetamt() {
		return netamt;
	}

	public void setNetamt(double netamt) {
		this.netamt = netamt;
	}

}
