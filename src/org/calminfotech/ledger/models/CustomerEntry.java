package org.calminfotech.ledger.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="GL_cust_entry")
public class CustomerEntry extends CommonLedger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name="amount")
	private float amount;
	
	@Column(name="post_code")
	private String post_code;
	
	@Column(name="account_no")
	private String account_no;
	
	@Column(name="description")
	private String description;
	
	@Column(name="batch_no")
	private String batch_no;
	
	@Column(name="ref_no2")
	private String ref_no2;
	
	@Column(name="posting_date")
	@Temporal(TemporalType.DATE)
	private Date posting_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public Date getPosting_date() {
		return posting_date;
	}

	public void setPosting_date(Date posting_date) {
		this.posting_date = posting_date;
	}

	public String getRef_no2() {
		return ref_no2;
	}

	public void setRef_no2(String ref_no2) {
		this.ref_no2 = ref_no2;
	}
	
	
	
}
