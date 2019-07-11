package org.calminfotech.billing.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.GlobalItemRanking;
import org.calminfotech.system.models.Organisation;

@SuppressWarnings("unused")
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bill_scheme_rankingprice")
// @SQLDelete(sql = "UPDATE bill_scheme SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class BillSchemeRankingPrice {

	@Id
	@GeneratedValue
	@Column(name = "id")
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
	@JoinColumn(name = "billscheme_id")
	private BillScheme billscheme;

	@ManyToOne
	@JoinColumn(name = "globalitemranking_id")
	private GlobalItemRanking globalitemranking;

	public GlobalItemRanking getGlobalitemranking() {
		return globalitemranking;
	}

	public void setGlobalitemranking(GlobalItemRanking globalitemranking) {
		this.globalitemranking = globalitemranking;
	}

	@Column(name = "price")
	private Double price;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

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

	public BillScheme getBillscheme() {
		return billscheme;
	}

	public void setBillscheme(BillScheme billscheme) {
		this.billscheme = billscheme;
	}

	/*
	 * 
	 * public GlobalItemUnitofMeasureVw getGlobalitemunit() { return
	 * globalitemunit; }
	 * 
	 * public void setGlobalitemunit(GlobalItemUnitofMeasureVw globalitemunit) {
	 * this.globalitemunit = globalitemunit; }
	 */
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
