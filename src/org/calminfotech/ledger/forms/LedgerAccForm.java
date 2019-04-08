package org.calminfotech.ledger.forms;

public class LedgerAccForm {

	private Integer id;
	
	private String name;
	
	private String accountNo;
	
	private String code;
	
	private String totalingCode;
	
	private Integer balSheetCatID;
	
	private Integer isActive;
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getTotalingCode() {
		return totalingCode;
	}

	public void setTotalingCode(String totalingCode) {
		this.totalingCode = totalingCode;
	}

	public Integer getBalSheetCatID() {
		return balSheetCatID;
	}

	public void setBalSheetCatID(Integer balSheetCatID) {
		this.balSheetCatID = balSheetCatID;
	}
	
	public Integer getIsActive() {
		return isActive;
	}
	
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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
