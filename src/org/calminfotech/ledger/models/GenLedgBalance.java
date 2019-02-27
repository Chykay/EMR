package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GL_balancing")
public class GenLedgBalance extends CommonLedger{

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="gl_account_no")
	private String gl_account_no;
	
	@Column(name="curr_balance")
	private float curr_balance;
	
	@Column(name="currency")
	private String currency;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	

}
