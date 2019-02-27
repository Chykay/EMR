package org.calminfotech.hmo.models;

//import java.sql.Blob;
//import java.util.List;
//import java.util.Set;

//import javax.persistence.CascadeType;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.utils.models.Billingwinsearch;
import org.calminfotech.utils.models.Hmostatus;
import org.calminfotech.utils.models.Period;


@Entity
@Table(name = "hmo_package_item_content")
public class HmoPackageItemContent {
	
	@Id
	@GeneratedValue
	@Column(name ="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "hmopackageitem_id")
	private HmoPackageItem hmoPackageItem;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Hmostatus hmostatus;

	@Column(name="description")
	private String description;

	@ManyToOne
	@JoinColumn(name="globalitem_id")
	private Billingwinsearch  billitem;
	
	@Column(name ="organisation_id")
	private Integer organisationId;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	

	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
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




	



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	public HmoPackageItem getHmoPackageItem() {
		return hmoPackageItem;
	}
	public void setHmoPackageItem(HmoPackageItem hmoPackageItem) {
		this.hmoPackageItem = hmoPackageItem;
	}
	public Billingwinsearch getBillitem() {
		return billitem;
	}
	public void setBillitem(Billingwinsearch billitem) {
		this.billitem = billitem;
	}
	
	
	
}

	
