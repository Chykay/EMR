package org.calminfotech.billing.models;

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

import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.utils.models.Billschemestatus;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bill_scheme")
// @SQLDelete(sql = "UPDATE bill_scheme SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class BillScheme {

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

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	// @Column(name = "billing_type")
	// private Integer billingType;

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

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Billschemestatus billschemestatus;

	@OneToMany
	@JoinColumn(name = "billingscheme_id")
	private Set<Hmo> hmoscheme;

	@OneToMany
	@JoinColumn(name = "billscheme_id")
	private Set<BillSchemeMeasurePrice> billschememeasure;

	public Set<BillSchemeMeasurePrice> getBillschememeasure() {
		return billschememeasure;
	}

	public void setBillschememeasure(
			Set<BillSchemeMeasurePrice> billschememeasure) {
		this.billschememeasure = billschememeasure;
	}

	public Set<Hmo> getHmoscheme() {
		return hmoscheme;
	}

	public void setHmoscheme(Set<Hmo> hmoscheme) {
		this.hmoscheme = hmoscheme;
	}

	public Billschemestatus getBillschemestatus() {
		return billschemestatus;
	}

	public void setBillschemestatus(Billschemestatus billschemestatus) {
		this.billschemestatus = billschemestatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
