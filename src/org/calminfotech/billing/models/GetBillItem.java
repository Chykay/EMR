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
public class GetBillItem {
	//variable
	

	@Id
	@Column(name="id")
	private Integer id;
	
	
	@Column(name="itemid")
	private Integer itemid;
	
	@Column(name="itemname")
	private String itemname;
	
	@Column(name="code")
	private String code;



	@Column(name="amt")
	private Double amt;

	
	@Column(name="visit_id")
	private Integer visit_id;

	

	@Column(name="reference_id")
	private Integer reference_id;
	
	
	@Column(name="measurename")
	private String measurename;
	


	@Column(name="itemmeasureid")
	private Integer itemmeasureid;
	


	@Column(name="qty")
	private Integer qty;
	


	@Column(name="unitid")
	private Integer unitid;
	
	
	
	
	
	

	public Integer getUnitid() {
		return unitid;
	}




	public void setUnitid(Integer unitid) {
		this.unitid = unitid;
	}




	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}




	public Integer getItemmeasureid() {
		return itemmeasureid;
	}




	public void setItemmeasureid(Integer itemmeasureid) {
		this.itemmeasureid = itemmeasureid;
	}




	public Integer getQty() {
		return qty;
	}




	public void setQty(Integer qty) {
		this.qty = qty;
	}




	public String getMeasurename() {
		return measurename;
	}




	public void setMeasurename(String measurename) {
		this.measurename = measurename;
	}




	public Integer getVisit_id() {
		return visit_id;
	}




	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}







	public String getCode() {
		return code;
	}




	public void setCode(String code) {
		this.code = code;
	}




	public Double getAmt() {
		return amt;
	}




	public void setAmt(Double amt) {
		this.amt = amt;
	}




	public Integer getReference_id() {
		return reference_id;
	}




	public void setReference_id(Integer reference_id) {
		this.reference_id = reference_id;
	}




	public Integer getItemid() {
		return itemid;
	}




	public void setItem_id(Integer itemid) {
		this.itemid = itemid;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getItemname() {
		return itemname;
	}




	public void setItemname(String itemname) {
		this.itemname = itemname;
	}



	
}
