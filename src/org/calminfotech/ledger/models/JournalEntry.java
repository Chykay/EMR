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
	
	@Column(name="journal_id")
	private String journal_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public Integer getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(Integer branch_id) {
		this.branch_id = branch_id;
	}

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getRef_no() {
		return ref_no;
	}

	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}

	public String getJournal_id() {
		return journal_id;
	}

	public void setJournal_id(String journal_id) {
		this.journal_id = journal_id;
	}
}

