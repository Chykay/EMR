package org.calminfotech.ledger.reports.models;

import java.util.List;

public class CompanyTB {
	
	private Integer id;

	private String name;

	private List<BranchTB> branchTBs;
	
	private float totCredit;
	
	private float totDebit;
	
	private float totBalance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BranchTB> getBranchTBs() {
		return branchTBs;
	}

	public void setBranchTBs(List<BranchTB> branchTBs) {
		this.branchTBs = branchTBs;
	}

	public float getTotCredit() {
		return totCredit;
	}

	public void setTotCredit(float totCredit) {
		this.totCredit = totCredit;
	}

	public float getTotDebit() {
		return totDebit;
	}

	public void setTotDebit(float totDebit) {
		this.totDebit = totDebit;
	}

	public float getTotBalance() {
		return totBalance;
	}

	public void setTotBalance(float totBalance) {
		this.totBalance = totBalance;
	}


}
