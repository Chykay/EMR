package org.calminfotech.ledger.reports.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class BranchBalSheet {
	
	@Id
	private Integer id;

	private String name;

	@OneToMany
	private List<BalanceSheet> balanceSheets;
	
	

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

	public List<BalanceSheet> getBalanceSheets() {
		return balanceSheets;
	}

	public void setBalanceSheets(List<BalanceSheet> balanceSheets) {
		this.balanceSheets = balanceSheets;
	}




}
