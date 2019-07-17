package org.calminfotech.ledger.reports.models;

import java.util.List;

/*@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)*/
public class BranchAccChart {
	
	//@Id
	private Integer id;

	private String name;
	
	private String companyName;
	
	private float balance;

	//@OneToMany
	private List<AccChartEntry> accChartEntries;

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

	public List<AccChartEntry> getAccChartEntries() {
		return accChartEntries;
	}

	public void setAccChartEntries(List<AccChartEntry> accChartEntries) {
		this.accChartEntries = accChartEntries;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	
	
}
