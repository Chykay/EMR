package org.calminfotech.ledger.forms;

public class GLPostingForm {
	private Integer id;
	
	private String create_date;
	
	private String pAccountNo;
	
	private Integer pBranchID;
	
	private float pBranchBal;
	
	private String pDescription;

	private String amount;

	private String posting_date;
	
	private float rAmount;
	
	private String pPostCode;
	
	private String rAccountNo;
	
	private Integer rBranchID;
	
	private float rBranchBal;
	
	private String rDescription;
	
	private String rPostCode;
	
	private String refNo1;
		
	private String refNo2;
	
	private String refNo3;

	private String pAccountType;

	private String rAccountType;
	
	private String currency = "NGN";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getCreateDate() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getPAccountNo() {
		return pAccountNo;
	}

	public void setPAccountNo(String pAccountNo) {
		this.pAccountNo = pAccountNo;
	}

	public float getPBranchBal() {
		return pBranchBal;
	}

	public void setPBranchBal(float pBranchBal) {
		this.pBranchBal = pBranchBal;
	}

	public String getPDescription() {
		return pDescription;
	}

	public void setPDescription(String p_description) {
		this.pDescription = p_description;
	}


	public String getPPostCode() {
		return pPostCode;
	}

	public void setPPostCode(String pPostCode) {
		this.pPostCode = pPostCode;
	}

	public String getRAccountNo() {
		return rAccountNo;
	}

	public void setRAccountNo(String rAccountNo) {
		this.rAccountNo = rAccountNo;
	}

	public Integer getRBranchID() {
		return rBranchID;
	}

	public void setRBranchID(Integer rBranchID) {
		this.rBranchID = rBranchID;
	}

	public float getRBranchBal() {
		return rBranchBal;
	}

	public void setRBranchBal(float rBranchBal) {
		this.rBranchBal = rBranchBal;
	}

	public String getRDescription() {
		return rDescription;
	}

	public void setRDescription(String rDescription) {
		this.rDescription = rDescription;
	}

	public String getRPostCode() {
		return rPostCode;
	}

	public void setRPostCode(String rPostCode) {
		this.rPostCode = rPostCode;
	}

	public String getCurrency() {
		return currency;
	}


	public Integer getPBranchID() {
		return pBranchID;
	}

	public void setPBranchID(Integer pBranchID) {
		this.pBranchID = pBranchID;
	}

	public String getRefNo1() {
		return refNo1;
	}

	public void setRefNo1(String refNo1) {
		this.refNo1 = refNo1;
	}

	public String getRefNo2() {
		return refNo2;
	}

	public void setRefNo2(String refNo2) {
		this.refNo2 = refNo2;
	}

	public String getRefNo3() {
		return refNo3;
	}

	public void setRefNo3(String refNo3) {
		this.refNo3 = refNo3;
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

	public float getRAmount() {
		return rAmount;
	}

	public void setRAmount(float rAmount) {
		this.rAmount = rAmount;
	}

	public String getPostingDate() {
		return posting_date;
	}

	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}

	public String getPAccountType() {
		return pAccountType;
	}
	
	public void setPAccountType(String pAccountType) {
		this.pAccountType = pAccountType;
	}

	public String getRAccountType() {
		return rAccountType;
	}

	public void setRAccountType(String rAccountType) {
		this.rAccountType = rAccountType;
	}

	
	
}
