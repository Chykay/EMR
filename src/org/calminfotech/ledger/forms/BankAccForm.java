package org.calminfotech.ledger.forms;

public class BankAccForm {
	
	private Integer id;
	
	private String bank;
	
	private String accountNo;
	
	private Integer ledgerAccID;

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

	public Integer getLedgerAccID() {
		return ledgerAccID;
	}

	public void setLedgerAccID(Integer ledgerAccID) {
		this.ledgerAccID = ledgerAccID;
	}

	
}
