package org.calminfotech.billing.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.Table;

import org.calminfotech.system.models.GlobalItemPoint;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.system.models.Organisation;

@Entity
@NamedNativeQuery(
		name = "spGetBillingInvoice",
		query = "{CALL sp_fetch_Price(:billingScheme,:unitofMeasure,:globalItemPoint,:organisation)}",
		callable = true,
		resultClass = BillSchemeItemPriceDetails.class
	)
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "billing_scheme_item_price_details")
public class BillSchemeItemPriceDetails {
	//variables
	private Integer id;
	private String price;
	private Organisation organisation;
	private String createdby;
	private Date createdDate;
	//reltionship
	private BillScheme billingScheme;
	public void setBillingScheme(BillScheme billingScheme) {
		this.billingScheme = billingScheme;
	}
	private GlobalUnitofMeasure globalUnitofMeasure;
	private GlobalItemPoint globalItemPoint;
	//setter and getters
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "price") 
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	@Column(name = "created_by")
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@ManyToOne
	@JoinColumn(name = "billingscheme_id")
	public BillScheme getBillingScheme() {
		return billingScheme;
	}
	
	@ManyToOne
	@JoinColumn(name = "unitofmeasure_id")
	public GlobalUnitofMeasure getUnitofMeasure() {
		return globalUnitofMeasure;
	}
	public void setUnitofMeasure(GlobalUnitofMeasure globalUnitofMeasure) {
		this.globalUnitofMeasure = globalUnitofMeasure;
	}
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	public GlobalItemPoint getGlobalItemPoint() {
		return globalItemPoint;
	}
	public void setGlobalItemPoint(GlobalItemPoint globalItemPoint) {
		this.globalItemPoint = globalItemPoint;
	}
}
