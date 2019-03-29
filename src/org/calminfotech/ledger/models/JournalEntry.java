package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GL_journal_table")
public class JournalEntry extends CommonLedger {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;

	@Column(name="p_account_type")
	private String p_account_type;

	@Column(name="p_account_no")
	private String p_account_no;
	
	@Column(name="p_branch_id")
	private Integer p_branch_id;
	
	@Column(name="p_post_code")
	private String p_post_code;
	
	@Column(name="p_description")
	private String p_description;
	
	@Column(name="r_account_type")
	private String r_account_type;
	
	@Column(name="r_account_no")
	private String r_account_no;
	
	@Column(name="r_branch_id")
	private Integer r_branch_id;
	
	@Column(name="r_post_code")
	private String r_post_code;
	
	@Column(name="r_description")
	private String r_description;

	@Column(name="amount")
	private float amount;
	
/*	@Column(name="posting_date")
	@Temporal(TemporalType.DATE)
	private Date posting_date;
	*/
	
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

	public String getP_account_type() {
		return p_account_type;
	}

	public void setP_account_type(String p_account_type) {
		this.p_account_type = p_account_type;
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

	public String getP_post_code() {
		return p_post_code;
	}

	public void setP_post_code(String p_post_code) {
		this.p_post_code = p_post_code;
	}

	public String getP_description() {
		return p_description;
	}

	public void setP_description(String p_description) {
		this.p_description = p_description;
	}

	public String getR_account_type() {
		return r_account_type;
	}

	public void setR_account_type(String r_account_type) {
		this.r_account_type = r_account_type;
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

	public String getR_post_code() {
		return r_post_code;
	}

	public void setR_post_code(String r_post_code) {
		this.r_post_code = r_post_code;
	}

	public String getR_description() {
		return r_description;
	}

	public void setR_description(String r_description) {
		this.r_description = r_description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	/*public Date getPosting_date() {
		return posting_date;
	}

	public void setPosting_date(Date posting_date) {
		this.posting_date = posting_date;
	}*/

	public String getRef_no1() {
		return ref_no;
	}

	public void setRef_no1(String ref_no) {
		this.ref_no = ref_no;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}

