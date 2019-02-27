package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
//@SQLDelete(sql = "UPDATE GeneralLedger SET is_deleted = 1 WHERE id = ?")
@Table(name = "GL_setup_table")
public class GeneralLedger extends CommonLedger{
	@Id
	@GeneratedValue
	@Column(name ="id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "account_no")
	private String account_no;
	

	@Column(name = "totaling_code")
	private String totaling_code;
	

	@Column(name = "bal_sheet_cat_id")
	private Integer bal_sheet_cat_id;
	 
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "is_active")
	private boolean is_active;
	
	@Transient
	private float balance;

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

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getTotaling_code() {
		return totaling_code;
	}

	public void setTotaling_code(String totaling_code) {
		this.totaling_code = totaling_code;
	}

	public Integer getBal_sheet_cat_id() {
		return bal_sheet_cat_id;
	}

	public void setBal_sheet_cat_id(Integer bal_sheet_cat_id) {
		this.bal_sheet_cat_id = bal_sheet_cat_id;
	}

	public boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	

}
