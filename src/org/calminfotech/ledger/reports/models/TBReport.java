package org.calminfotech.ledger.reports.models;

import java.util.List;

public class TBReport {

	private Integer id;

	private String name;
	

	private List<TrialBalEntry> entries;
	
	private float totCredit;
	
	private float totDebit;
	
	private float totBalance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TrialBalEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<TrialBalEntry> tBalEntries) {
		this.entries = tBalEntries;
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
