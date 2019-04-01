package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GL_journal_table")
public class JournalEntry extends CommonLedger {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;

	@Column(name="account_type")
	private String account_type;

	@Column(name="account_no")
	private String account_no;
	
	@Column(name="branch_id")
	private Integer branch_id;
	
	@Column(name="post_code")
	private String post_code;
	
	@Column(name="description")
	private String description;

	@Column(name="amount")
	private float amount;

	@Column(name="ref_no")
	private String ref_no;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getaccount_type() {
		return account_type;
	}

	public void setaccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getaccount_no() {
		return account_no;
	}

	public void setaccount_no(String account_no) {
		this.account_no = account_no;
	}

	public Integer getbranch_id() {
		return branch_id;
	}

	public void setbranch_id(Integer branch_id) {
		this.branch_id = branch_id;
	}

	public String getpost_code() {
		return post_code;
	}

	public void setpost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	
	public String getRef_no1() {
		return ref_no;
	}

	public void setRef_no1(String ref_no) {
		this.ref_no = ref_no;
	}

}

