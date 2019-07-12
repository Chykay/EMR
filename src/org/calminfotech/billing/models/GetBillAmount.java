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
public class GetBillAmount {
	//variable
	

	@Id
	@Column(name="amt")
	private Double amt;

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}
	
}
