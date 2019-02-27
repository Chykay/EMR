package org.calminfotech.ledger.forms;

public class TotalingForm {

	private Integer id;
	
	private String name;
	
	private String ledger_type;
	
	private String code;
	
	private Integer is_active;
	
	public String getLedger_type() {
		return ledger_type;
	}

	public void setLedger_type(String ledger_type) {
		this.ledger_type = ledger_type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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


