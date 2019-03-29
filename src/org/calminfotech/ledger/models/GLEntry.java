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
@Table(name="GL_entry")
public class GLEntry extends CommonLedger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name="amount")
	private float amount;
	
	@Column(name="ref_no1")
	private String ref_no1;
	
	@Column(name="ref_no2")
	private String ref_no2;
	
	@Column(name="post_code")
	private String post_code;
	
	@Column(name="account_no")
	private String account_no;
	
	@Column(name="branch")
	private Integer branch;
	
	@Column(name="description")
	private String description;
	
	@Column(name="batch_no")
	private String batch_no;
	
	@Column(name="posting_date")
	@Temporal(TemporalType.DATE)
	private Date posting_date;
	
	@Column(name="product_id")
	private String product_id;
	
	@Column(name="product_type")
	private String product_type;

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

	public String getRef_no1() {
		return ref_no1;
	}

	public void setRef_no1(String ref_no1) {
		this.ref_no1 = ref_no1;
	}

	
	public String getRef_no2() {
		return ref_no2;
	}

	public void setRef_no2(String ref_no2) {
		this.ref_no2 = ref_no2;
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

	public Integer getBranch() {
		return branch;
	}

	public void setBranch(Integer branch) {
		this.branch = branch;
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

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
}
