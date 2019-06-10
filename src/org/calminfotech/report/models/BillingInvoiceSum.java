package org.calminfotech.report.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class BillingInvoiceSum {

	@Id
	@GeneratedValue
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "Monthname")
	private String Monthname;

	@Column(name = "yearname")
	private String yearname;

	@Column(name = "Grandtotal")
	private Double Grandtotal;

	public Double getGrandtotal() {
		return Grandtotal;
	}

	public void setGrandtotal(Double grandtotal) {
		Grandtotal = grandtotal;
	}

	@Column(name = "organisation_id")
	private Integer organisationId;

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getMonthname() {
		return Monthname;
	}

	public void setMonthname(String monthname) {
		Monthname = monthname;
	}

	public String getYearname() {
		return yearname;
	}

	public void setYearname(String yearname) {
		this.yearname = yearname;
	}

}
