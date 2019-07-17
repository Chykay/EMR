package org.calminfotech.billing.models;

//import java.sql.Blob;
//import java.util.List;
//import java.util.Set;

//import javax.persistence.CascadeType;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "bill_scheme_item")
@org.hibernate.annotations.Entity(dynamicInsert = true)
@SQLDelete(sql = "UPDATE bill_scheme_item SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class BillSchemeItem {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "description")
	private String billName;

	@Column(name = "global_item_id")
	private Integer globalItemId;

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
	@JoinColumn(name = "bill_id")
	private BillScheme billScheme;

	@OneToMany
	@JoinColumn(name = "bill_scheme_item_id")
	private Set<BillSchemeItemPrice> billSchemeItemPrice;

	public Integer getId() {
		return id;
	}

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	public Set<BillSchemeItemPrice> getBillItemPrice() {
		return billSchemeItemPrice;
	}

	public void setBillItemPrice(Set<BillSchemeItemPrice> billSchemeItemPrice) {
		this.billSchemeItemPrice = billSchemeItemPrice;
	}

	public BillScheme getBillScheme() {
		return billScheme;
	}

	public void setBillScheme(BillScheme billScheme) {
		this.billScheme = billScheme;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<BillSchemeItemPrice> getBillSchemeItemPrice() {
		return billSchemeItemPrice;
	}

	public void setBillSchemeItemPrice(
			Set<BillSchemeItemPrice> billSchemeItemPrice) {
		this.billSchemeItemPrice = billSchemeItemPrice;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public Integer getGlobalItemId() {
		return globalItemId;
	}

	public void setGlobalItemId(Integer globalItemId) {
		this.globalItemId = globalItemId;
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

}
