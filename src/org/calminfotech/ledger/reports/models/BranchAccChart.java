package org.calminfotech.ledger.reports.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class BranchAccChart {
	
	@Id
	private Integer id;

	private String name;

	@OneToMany
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
}
