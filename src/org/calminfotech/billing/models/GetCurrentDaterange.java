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
public class GetCurrentDaterange {
	//variable

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nohmousedforitem")
	private Integer nohmousedforitem;

	
	@Column(name="hmoamountusedforitem")
	private Integer hmoamountusedforitem; 
	
	

	@Temporal(TemporalType.DATE)
 	@Column(name = "trandat")
	private Date trandat;
	
		 	
	
	@Temporal(TemporalType.DATE)
 	@Column(name = "startrange")
	private Date startrange;
	
		 	
	@Temporal(TemporalType.DATE)
 	@Column(name = "endrange")
	private Date endrange;

	@Column(name = "spedinglimit")
	private Double spendinglimit;
	
	@Column(name = "nooftimeslimit")
	private Integer nooftimeslimit;
	
	@Column(name = "packageitemid")
	private Integer packageitemid;
	
	
	
	public Date getTrandat() {
		return trandat;
	}


	public void setTrandat(Date trandat) {
		this.trandat = trandat;
	}


	public Double getSpendinglimit() {
		return spendinglimit;
	}


	public void setSpendinglimit(Double spendinglimit) {
		this.spendinglimit = spendinglimit;
	}


	public Integer getNooftimeslimit() {
		return nooftimeslimit;
	}


	public void setNooftimeslimit(Integer nooftimeslimit) {
		this.nooftimeslimit = nooftimeslimit;
	}


	public Integer getPackageitemid() {
		return packageitemid;
	}


	public void setPackageitemid(Integer packageitemid) {
		this.packageitemid = packageitemid;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNohmousedforitem() {
		return nohmousedforitem;
	}


	public void setNohmousedforitem(Integer nohmousedforitem) {
		this.nohmousedforitem = nohmousedforitem;
	}


	public Integer getHmoamountusedforitem() {
		return hmoamountusedforitem;
	}


	public void setHmoamountusedforitem(Integer hmoamountusedforitem) {
		this.hmoamountusedforitem = hmoamountusedforitem;
	}


	public Date getStartrange() {
		return startrange;
	}


	public void setStartrange(Date startrange) {
		this.startrange = startrange;
	}


	public Date getEndrange() {
		return endrange;
	}


	public void setEndrange(Date endrange) {
		this.endrange = endrange;
	}
	
	
	
}
