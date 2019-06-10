package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@SQLDelete(sql = "UPDATE GL_setup_table SET is_active = 0 WHERE id = ?")
@Table(name = "GL_setup_table")
public class LedgerAccount extends CommonLedger{
	
	@Id
	@GeneratedValue
	@Column(name ="id")
	private int id;
	
	@Column(name = "name", unique=true, nullable=false)
	private String name;
	
	@Column(name = "account_no", unique=true, nullable=false)
	private String accountNo;
	

	@Column(name = "totaling_code")
	private String totalingCode;
	

	@Column(name = "ledger_cat_id")
	private Integer ledgerCatID;
	 
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Transient
	private float balance;
	
	@Transient
	private float amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTotalingCode() {
		return totalingCode;
	}

	public void setTotalingCode(String totalingCode) {
		this.totalingCode = totalingCode;
	}

	public Integer getLedgerCatID() {
		return ledgerCatID;
	}

	public void setLedgerCatID(Integer balSheetCatID) {
		this.ledgerCatID = balSheetCatID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}


	
}
