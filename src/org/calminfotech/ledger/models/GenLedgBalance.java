package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GL_balancing")
public class GenLedgBalance extends CommonLedger{

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="gl_account_no")
	private String gl_account_no;
	
	@Column(name="curr_balance")
	private float curr_balance;
	
	@Column(name="currency")
	private String currency;
	
/*	@Column(name="branch_id")
	private Integer branch_id;
	
	@Column(name="company_id")
	private Integer company_id;*/
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGl_account_no() {
		return gl_account_no;
	}

	public void setGl_account_no(String gl_account_no) {
		this.gl_account_no = gl_account_no;
	}

	public float getCurr_balance() {
		return curr_balance;
	}

	public void setCurr_balance(float curr_balance) {
		this.curr_balance = curr_balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/*
	 * 
	 * public OrganisationCompany getOrgCoy() {
		return orgCoy;
	}

	public void setOrgCoy(OrganisationCompany orgCoy) {
		this.orgCoy = orgCoy;
	}
	
	public Integer getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(Integer branch_id) {
		this.branch_id = branch_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}*/

	

}
