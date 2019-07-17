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

import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.Organisation;
//import org.calminfotech.views.models.VwItem;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
//@Table(name = "billing_invoice")
//@SQLDelete(sql = "UPDATE billing_invoice SET is_deleted = 1 WHERE id = ?")
//@Where(clause = "is_deleted <> 1")
public class Chargeto {
	//variable

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="globalitemid")
	private Integer globalitemid;
	
	


	public String getGlobalitemname() {
		return globalitemname;
	}


	public void setGlobalitemname(String globalitemname) {
		this.globalitemname = globalitemname;
	}


	public String getItemmeasurename() {
		return itemmeasurename;
	}


	public void setItemmeasurename(String itemmeasurename) {
		this.itemmeasurename = itemmeasurename;
	}


	@Column(name="globalitemname")
	private String globalitemname;
	
	@Column(name="itemmeasureid")
	private Integer itemmeasureid;
	
	@Column(name="itemmeasurename")
	private String itemmeasurename;
	
	@Column(name="unitid")
	private Integer unitid;
	
	@Column(name="qty")
	private Integer qty;
	
		
	@Column(name="patienthmopackageid")
	private Integer patienthmopackageid;

	
	@Column(name="hmoid")
	private Integer hmoid;   
	
	@Column(name="hmoname")
	private String hmoname;
	
	@Column(name="hmopackageid")
	private Integer hmopackageid;
	
	@Column(name="packageitemid")
	private Integer hmopackageitemid;
	
	

	@Column(name="packageitemname")
	private String hmopackageitemname;
	
	
	
	@Column(name="hpname")
    private String hpname;
	
	@Column(name="patientid")
	private Integer patientid;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="visitid")
	private Integer visitid;
	
	@Column(name="code")
	private String code;
	
	@Column(name="referenceid")
	private String referenceid;
	
	//@Column(name="description")
	//private String description;
	
	
	@Column(name="billschemeid")
	private Integer billschemeid;
	
	@Column(name="billschemename")
	private String billschemename;
	
	
	@Temporal(TemporalType.DATE)
 	@Column(name = "fromdat")
	private Date fromdat;
	
		 	
	@Temporal(TemporalType.DATE)
 	@Column(name = "todat")
	private Date todat;

   @Column(name="invamt")
   private Double invamt;


   @Column(name="cashamt")
   private Double cashamt;



   @Column(name="hmoamt")
   private Double hmoamt;


   

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


	public String getReferenceid() {
	return referenceid;
}


public void setReferenceid(String referenceid) {
	this.referenceid = referenceid;
}


public Double getInvamt() {
	return invamt;
}


public void setInvamt(Double invamt) {
	this.invamt = invamt;
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

	
	public Integer getHmopackageitemid() {
		return hmopackageitemid;
	}


	public void setHmopackageitemid(Integer hmopackageitemid) {
		this.hmopackageitemid = hmopackageitemid;
	}


	public String getHmopackageitemname() {
		return hmopackageitemname;
	}


	public void setHmopackageitemname(String hmopackageitemname) {
		this.hmopackageitemname = hmopackageitemname;
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
