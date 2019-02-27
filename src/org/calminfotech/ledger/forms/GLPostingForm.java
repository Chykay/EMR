package org.calminfotech.ledger.forms;

public class GLPostingForm {
	private Integer id;
	
	private String create_date;
	
	private String p_account_no;
	
	private float p_branch_bal;
	
	private String p_description;
	
	private float p_amount;
	
	private String p_post_code;
	
	private String r_account_no;
	
	private Integer r_branch_id;
	
	private float r_branch_bal;
	
	private String r_description;
	
	private float r_amount;
	
	private String r_post_code;
	
	private String ref_no;
	
	private String currency = "NGN";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getP_account_no() {
		return p_account_no;
	}

	public void setP_account_no(String p_account_no) {
		this.p_account_no = p_account_no;
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

	public String getCurrency() {
		return currency;
	}

	public String getRef_no() {
		return ref_no;
	}

	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
