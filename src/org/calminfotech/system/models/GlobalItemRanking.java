package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
import org.calminfotech.billing.models.BillSchemeRankingPrice;*/
import org.calminfotech.hrunit.models.Staffgroupranking;

@SuppressWarnings("unused")
@Entity
@Table(name = "globalitem_Ranking")
public class GlobalItemRanking {
	// Variables

	// getter and setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@ManyToOne
	@JoinColumn(name = "ranking_id")
	private Staffgroupranking staffranking;

	@ManyToOne
	@JoinColumn(name = "globalitem_id")
	private GlobalItem globalitem;

	/*@OneToMany
	@JoinColumn(name = "globalitemranking_id")
	private Set<BillSchemeRankingPrice> billschemerankingprice;
*/
	public Staffgroupranking getStaffranking() {
		return staffranking;
	}

	public void setStaffranking(Staffgroupranking staffranking) {
		this.staffranking = staffranking;
	}

	/*public Set<BillSchemeRankingPrice> getBillschemerankingprice() {
		return billschemerankingprice;
	}*/

/*	public void setBillschemerankingprice(
			Set<BillSchemeRankingPrice> billschemerankingprice) {
		this.billschemerankingprice = billschemerankingprice;
	}*/

	@Column(name = "created_by")
	private String createdby;

	@Column(name = "created_Date")
	private Date createDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GlobalItem getGlobalitem() {
		return globalitem;
	}

	public void setGlobalitem(GlobalItem globalitem) {
		this.globalitem = globalitem;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}