package org.calminfotech.hrunit.models;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "unit_view")
public class HrUnitView {
	
	@Id
	@Column(name = "rowid")
	private Integer rowId;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "unitname")
	private String unitName;
	
	@Column(name = "pointname")
	private String pointName;
	
	@Column(name = "billingscheme")
	private String billingScheme;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@Column(name = "create_date")
	private Date createdDate;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getBillingScheme() {
		return billingScheme;
	}

	public void setBillingScheme(String billingScheme) {
		this.billingScheme = billingScheme;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	

}
