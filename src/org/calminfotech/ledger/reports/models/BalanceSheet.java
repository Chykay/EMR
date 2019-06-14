package org.calminfotech.ledger.reports.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class BalanceSheet {
	@Id
	private Integer id;
	
	private String name;
	
	private String accountNo;
	
	private float totBalance;
	
	private Integer parentID;
	
	private Integer hasChildren;
	
	@OneToMany
	private List<BalanceSheet> balanceSheets;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public float getTotBalance() {
		return totBalance;
	}

	public void setTotBalance(float totBalance) {
		this.totBalance = totBalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BalanceSheet> getBalanceSheets() {
		return balanceSheets;
	}

	public void setBalanceSheets(List<BalanceSheet> balanceSheets) {
		this.balanceSheets = balanceSheets;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(Integer hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	
	
	
}
