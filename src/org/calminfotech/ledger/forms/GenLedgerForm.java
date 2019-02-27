package org.calminfotech.ledger.forms;

public class GenLedgerForm {

	private Integer id;
	
	private String name;
	
	private String account_no;
	
	private String code;
	
	private String totaling_code;
	
	private Integer bal_sheet_cat_id;
	
	private Integer is_active;
	
	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	
	public Integer getIs_active() {
		return is_active;
	}
	
	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
