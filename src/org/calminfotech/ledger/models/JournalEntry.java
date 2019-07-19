package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;

@Entity
@Table(name="GL_journal_entry")
public class JournalEntry extends CommonLedger {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;

	@Column(name="account_type")
	private String accountType;

	@Column(name="account_no")
	private String accountNo;
	
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Organisation branch;
	
	@Column(name="post_code")
	private String postCode;
	
	@Column(name="description")
	private String description;

	@Column(name="amount")
	private float amount;

	@Column(name="ref_no")
	private String refNo;
	
	@Column(name="journal_id")
	private String journalID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Organisation getBranch() {
		return branch;
	}

	public void setBranch(Organisation branch) {
		this.branch = branch;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
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

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getJournalID() {
		return journalID;
	}

	public void setJournalID(String journalID) {
		this.journalID = journalID;
	}

	
}

