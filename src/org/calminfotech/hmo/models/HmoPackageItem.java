package org.calminfotech.hmo.models;

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
import org.calminfotech.utils.models.Billingwinsearch;
import org.calminfotech.utils.models.Hmostatus;
import org.calminfotech.utils.models.Period;

//import javax.persistence.FetchType;

@Entity
@Table(name = "hmo_package_item")
public class HmoPackageItem {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "spending_limit")
	private String spendingLimit;

	@Column(name = "is_all")
	private Boolean Isall;

	@Column(name = "useaspercent")
	private Boolean useaspercent;

	@Column(name = "period_no")
	private Integer periodNo;

	@Column(name = "time_no")
	private Integer timeNo;

	@ManyToOne
	@JoinColumn(name = "globalitem_id")
	private Billingwinsearch billitem;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Boolean getUseaspercent() {
		return useaspercent;
	}

	public void setUseaspercent(Boolean useaspercent) {
		this.useaspercent = useaspercent;
	}

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@OneToMany
	@JoinColumn(name = "hmopackageitem_id")
	private Set<HmoPackageItemContent> hmopackageItemContent;

	public Billingwinsearch getBillitem() {
		return billitem;
	}

	public void setBillitem(Billingwinsearch billitem) {
		this.billitem = billitem;
	}

	public Boolean getIsall() {
		return Isall;
	}

	public void setIsall(Boolean isall) {
		Isall = isall;
	}

	public Set<HmoPackageItemContent> getHmopackageItemContent() {
		return hmopackageItemContent;
	}

	public void setHmopackageItemContent(
			Set<HmoPackageItemContent> hmopackageItemContent) {
		this.hmopackageItemContent = hmopackageItemContent;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public Hmostatus getHmostatus() {
		return hmostatus;
	}

	public void setHmostatus(Hmostatus hmostatus) {
		this.hmostatus = hmostatus;
	}

	@ManyToOne
	@JoinColumn(name = "hmopackage_id")
	private HmoPackage hmoPackage;

	@ManyToOne
	@JoinColumn(name = "period_id")
	private Period period;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Hmostatus hmostatus;

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

	public HmoPackage getHmoPackage() {
		return hmoPackage;
	}

	public void setHmoPackage(HmoPackage hmoPackage) {
		this.hmoPackage = hmoPackage;
	}

	public String getSpendingLimit() {
		return spendingLimit;
	}

	public void setSpendingLimit(String spendingLimit) {
		this.spendingLimit = spendingLimit;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Integer getTimeNo() {
		return timeNo;
	}

	public void setTimeNo(Integer timeNo) {
		this.timeNo = timeNo;
	}

	public Integer getPeriodNo() {
		return periodNo;
	}

	public void setPeriodNo(Integer periodNo) {
		this.periodNo = periodNo;
	}

}
