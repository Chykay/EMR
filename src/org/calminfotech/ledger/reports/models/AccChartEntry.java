package org.calminfotech.ledger.reports.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class AccChartEntry {
	@Id
	private Integer id;
	
	private String name;
	
	private String accountNo;
	
	private float totBalance;
	
	private Integer parentID;
	
	private Integer hasChildren;
	
	private Integer show;
	
	@OneToMany
	private List<AccChartEntry> accChartEntries;

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

	
	public List<AccChartEntry> getAccChartEntries() {
		return accChartEntries;
	}

	public void setAccChartEntries(List<AccChartEntry> accChartEntries) {
		this.accChartEntries = accChartEntries;
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

	public Integer getShow() {
		return show;
	}

	public void setShow(Integer show) {
		this.show = show;
	}
	
	
	
	
}
