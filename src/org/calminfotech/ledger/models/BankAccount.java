package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity()
@Table(name="GL_bank_acc", uniqueConstraints = @UniqueConstraint(columnNames = {"organisation_id", "account_no", "ledger_id"}))
public class BankAccount extends CommonLedger{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="bank")
	private String bank;
	
	@Column(name="account_no")
	private String accountNo;
	
	@OneToOne
	@JoinColumn(name = "ledger_id")
	private LedgerAccount ledgerAccount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public LedgerAccount getLedgerAccount() {
		return ledgerAccount;
	}

	public void setLedgerAccount(LedgerAccount ledgerAccount) {
		this.ledgerAccount = ledgerAccount;
	}
	
	
}
