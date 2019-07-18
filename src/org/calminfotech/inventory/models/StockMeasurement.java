package org.calminfotech.inventory.models;

import java.util.Date;

//import org.calminfotech.consultation.models.VisitConsultationPrescribedDrug;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Prescribedstatus;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;

public class StockMeasurement {

	private Integer id;

	//private VisitConsultationPrescribedDrug consultationprescribedDrug;
	
	private String measurename;
	

	private Integer quantity;
	
	private Integer qtydisp;
	
	private String createdBy;

	private Date createdDate;
	
	private String modifiedBy;

	private Date modifydate;
	
	private boolean isDeleted;
	
	private boolean isActive;
	
	private Prescribedstatus  prescribedstatus;
	
	private User given_by;
	
	private HrunitCategory given_unit;
	
	private VisitWorkflowPoint given_point;
	

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
	
	
	

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	


	public String getMeasurename() {
		return measurename;
	}

	public void setMeasurename(String measurename) {
		this.measurename = measurename;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQtydisp() {
		return qtydisp;
	}

	public void setQtydisp(Integer qtydisp) {
		this.qtydisp = qtydisp;
	}

	public Prescribedstatus getPrescribedstatus() {
		return prescribedstatus;
	}

	public void setPrescribedstatus(Prescribedstatus prescribedstatus) {
		this.prescribedstatus = prescribedstatus;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	

	public Prescribedstatus getPrescribedststatus() {
		return prescribedstatus;
	}

	public void setPrescribedststatus(Prescribedstatus prescribedststatus) {
		this.prescribedstatus = prescribedststatus;
	}

	public User getGiven_by() {
		return given_by;
	}

	public void setGiven_by(User given_by) {
		this.given_by = given_by;
	}

	public HrunitCategory getGiven_unit() {
		return given_unit;
	}

	public void setGiven_unit(HrunitCategory given_unit) {
		this.given_unit = given_unit;
	}

	public VisitWorkflowPoint getGiven_point() {
		return given_point;
	}

	public void setGiven_point(VisitWorkflowPoint given_point) {
		this.given_point = given_point;
	}

	
	
	
	
}
