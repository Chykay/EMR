package org.calminfotech.ledger.forms;

public class LedgerCatForm {

	private Integer id;
	
	private String name;
	
	private Integer parentID;
	
	private Integer isActive;
	

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

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public Integer getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(Integer ledgerType) {
		this.ledgerType = ledgerType;
	}
	*/
	

}
