package org.calminfotech.inventory.models;

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

import org.calminfotech.system.models.Organisation;

@Entity
@Table(name = "storeissue_log")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class Storeissue_log implements java.io.Serializable {

	private static final long serialVersionUID = -9160881819374280018L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "requestid")
	private PointRequestLine pointrequestline;

	@Column(name = "action")
	private String action;

	@Column(name = "qty")
	private Integer qty;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "batchno")
	private String batchno;

	@Column(name = "manulicno")
	private String manulicno;

	@Column(name = "otherinfo")
	private String otherinfo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dispensedate")
	private Date dispensedate;

	@Temporal(TemporalType.DATE)
	@Column(name = "manufacturedate")
	private Date manufacturedate;

	@Temporal(TemporalType.DATE)
	@Column(name = "expirydate")
	private Date expirydate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modify_date")
	private Date modifydate;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
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

	public PointRequestLine getPointrequestline() {
		return pointrequestline;
	}

	public void setPointrequestline(PointRequestLine pointrequestline) {
		this.pointrequestline = pointrequestline;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getManulicno() {
		return manulicno;
	}

	public void setManulicno(String manulicno) {
		this.manulicno = manulicno;
	}

	public String getOtherinfo() {
		return otherinfo;
	}

	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}

	public Date getDispensedate() {
		return dispensedate;
	}

	public void setDispensedate(Date dispensedate) {
		this.dispensedate = dispensedate;
	}

	public Date getManufacturedate() {
		return manufacturedate;
	}

	public void setManufacturedate(Date manufacturedate) {
		this.manufacturedate = manufacturedate;
	}

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

}