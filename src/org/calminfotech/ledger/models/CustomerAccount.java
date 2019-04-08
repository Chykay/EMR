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
@Table(name = "GL_customer_table")
public class CustomerAccount extends CommonLedger{
	
	@Id
	@GeneratedValue
	@Column(name ="id")
	private int id;
	
	@Column(name = "patient_id")
	private String patientID;
	
	@Column(name = "account_no", unique=true, nullable=false)
	private String accountNo;
	
	@Column(name="curr_balance")
	private float currBalance;

	@Column(name = "is_active")
	private boolean isActive;
	
	@Transient
	private float amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getCurr_balance() {
		return currBalance;
	}

	public void setCurrBalance(float curr_balance) {
		this.currBalance = curr_balance;
	}

	

}
