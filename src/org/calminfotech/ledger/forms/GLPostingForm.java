package org.calminfotech.ledger.forms;

public class GLPostingForm {
	private Integer id;
	
	private String create_date;
	
	private String p_account_no;
	
	private Integer p_branch_id;
	
	private float p_branch_bal;
	
	private String p_description;

	private String amount;

	private String posting_date;
	
	private float r_amount;
	
	private String p_post_code;
	
	private String r_account_no;
	
	private Integer r_branch_id;
	
	private float r_branch_bal;
	
	private String r_description;
	
	private String r_post_code;
	
	private String ref_no1;
		
	private String ref_no2;
	
	private String ref_no3;

	private String p_account_type;

	private String r_account_type;
	
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

	public String getR_post_code() {
		return r_post_code;
	}

	public void setR_post_code(String r_post_code) {
		this.r_post_code = r_post_code;
	}

	public String getCurrency() {
		return currency;
	}


	public Integer getP_branch_id() {
		return p_branch_id;
	}

	public void setP_branch_id(Integer p_branch_id) {
		this.p_branch_id = p_branch_id;
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

	public String getRef_no3() {
		return ref_no3;
	}

	public void setRef_no3(String ref_no3) {
		this.ref_no3 = ref_no3;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public float getR_amount() {
		return r_amount;
	}

	public void setR_amount(float r_amount) {
		this.r_amount = r_amount;
	}

	public String getPosting_date() {
		return posting_date;
	}

	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}

	public String getP_account_type() {
		return p_account_type;
	}
	
	public void setP_account_type(String p_account_type) {
		this.p_account_type = p_account_type;
	}

	public String getR_account_type() {
		return r_account_type;
	}

	public void setR_account_type(String r_account_type) {
		this.r_account_type = r_account_type;
	}

	
	
}
