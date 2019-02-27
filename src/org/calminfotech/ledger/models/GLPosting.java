package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GL_postings")
public class GLPosting extends CommonLedger {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="p_account_no")
	private String p_account_no;
	
	@Column(name="p_branch_id")
	private Integer p_branch_id;
	
	@Column(name="p_branch_bal")
	private float p_branch_bal;
	
	@Column(name="p_description")
	private String p_description;
	
	@Column(name="p_amount")
	private float p_amount;
	
	@Column(name="p_post_code")
	private String p_post_code;
	
	@Column(name="r_account_no")
	private String r_account_no;
	
	@Column(name="r_branch_id")
	private Integer r_branch_id;
	
	@Column(name="r_branch_bal")
	private float r_branch_bal;
	
	@Column(name="r_description")
	private String r_description;
	
	@Column(name="r_amount")
	private float r_amount;
	
	@Column(name="r_post_code")
	private String r_post_code;
	
	@Column(name="ref_no")
	private String ref_no;
	
	@Column(name="currency")
	private String currency = "NGN";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getP_account_no() {
		return p_account_no;
	}

	public void setP_account_no(String p_account_no) {
		this.p_account_no = p_account_no;
	}

	public Integer getP_branch_id() {
		return p_branch_id;
	}

	public void setP_branch_id(Integer p_branch_id) {
		this.p_branch_id = p_branch_id;
	}

	public float getP_branch_bal() {
		return p_branch_bal;
	}

	public void setP_branch_bal(float p_branch_bal) {
		this.p_branch_bal = p_branch_bal;
	}

	public String getP_description() {
		return p_description;
	}

	public void setP_description(String p_description) {
		this.p_description = p_description;
	}

	public float getP_amount() {
		return p_amount;
	}

	public void setP_amount(float p_amount) {
		this.p_amount = p_amount;
	}

	public String getP_post_code() {
		return p_post_code;
	}

	public void setP_post_code(String p_post_code) {
		this.p_post_code = p_post_code;
	}

	public String getR_account_no() {
		return r_account_no;
	}

	public void setR_account_no(String r_account_no) {
		this.r_account_no = r_account_no;
	}

	public Integer getR_branch_id() {
		return r_branch_id;
	}

	public void setR_branch_id(Integer r_branch_id) {
		this.r_branch_id = r_branch_id;
	}

	public float getR_branch_bal() {
		return r_branch_bal;
	}

	public void setR_branch_bal(float r_branch_bal) {
		this.r_branch_bal = r_branch_bal;
	}

	public String getR_description() {
		return r_description;
	}

	public void setR_description(String r_description) {
		this.r_description = r_description;
	}

	public float getR_amount() {
		return r_amount;
	}

	public void setR_amount(float r_amount) {
		this.r_amount = r_amount;
	}

	public String getR_post_code() {
		return r_post_code;
	}

	public void setR_post_code(String r_post_code) {
		this.r_post_code = r_post_code;
	}
	
	public String getRef_no() {
		return ref_no;
	}

	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}

