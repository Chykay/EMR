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
	private String patient_id;
	
	@Column(name = "account_no", unique=true, nullable=false)
	private String account_no;
	
	@Column(name="curr_balance")
	private float curr_balance;

	@Column(name = "is_active")
	private boolean is_active;
	
	@Transient
	private float amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}


	public boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getCurr_balance() {
		return curr_balance;
	}

	public void setCurr_balance(float curr_balance) {
		this.curr_balance = curr_balance;
	}

	

}
