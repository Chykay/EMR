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

import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
//import org.calminfotech.views.models.VwItem;
import org.hibernate.annotations.SQLDelete;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "billing_invoice")
@SQLDelete(sql = "UPDATE billing_invoice SET is_deleted = 1 WHERE id = ?")
//@Where(clause = "is_deleted <> 1")
public class BillingInvoice {
	//variable
	
	private Integer id;
	//private LoginSection section;
	private Integer scheme;
	private VisitWorkflowPoint point;
	private int patienthmopackageid;
	private int hmopackageid;
	
	private GlobalItem item;
	private Visit visit;
	private Patient patient;
	private String quantity;
	private String unitId;
	private double price;
	private double amountPaid;
	private Organisation organisation;
	private String status;
	private String createdby;
	private Date createDate;
	private boolean isDeleted;
	//getters and setters
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	@Column(name = "scheme_id")
	public Integer getScheme() {
		return scheme;
	}
	public void setScheme(Integer scheme_id) {
		this.scheme = scheme_id;
	}
	
	@ManyToOne
	@JoinColumn(name = "point_id")
	public VisitWorkflowPoint getPoint() {
		return point;
	}
	public void setPoint(VisitWorkflowPoint point) {
		this.point = point;
	}
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	
	public GlobalItem getItem() {
		return item;
	}
	public void setItem(GlobalItem item) {
		this.item = item;
	}
	@ManyToOne
	@JoinColumn(name = "visit_id")
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@Column(name = "qty")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "unit")
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Column(name = "price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name = "amount_paid")
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	
	@Column(name = "patienthmopackageid")
	public int getPatienthmopackageid() {
		return patienthmopackageid;
	}
	public void setPatienthmopackageid(int patienthmopackageid) {
		this.patienthmopackageid = patienthmopackageid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "created_by")
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "is_deleted")
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name ="hmopackageid")
	public int getHmopackageid() {
		return hmopackageid;
	}
	public void setHmopackageid(int hmopackageid) {
		this.hmopackageid = hmopackageid;
	}
	public void setPrice(Object price2) {
		// TODO Auto-generated method stub
		
	}
	
}
