package org.calminfotech.ledger.reports.models;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class BranchTB {

	private String name;
	
	private List<TrialBalEntry> tBalEntries;
	
	private float totCredit;
	
	private float totDebit;
	
	private float totBalance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TrialBalEntry> getTBalEntries() {
		return tBalEntries;
	}

	public void setTBalEntries(List<TrialBalEntry> tBalEntries) {
		this.tBalEntries = tBalEntries;
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
