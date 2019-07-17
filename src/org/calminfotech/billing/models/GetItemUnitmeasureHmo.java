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
public class GetItemUnitmeasureHmo {
	//variable
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="itemunitmeasureid")
	private Integer itemunitmeasureid;

	@Column(name="name")
	private String name;

	public Integer getItemunitmeasureid() {
		return itemunitmeasureid;
	}

	public void setItemunitmeasureid(Integer itemunitmeasureid) {
		this.itemunitmeasureid = itemunitmeasureid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
