package org.calminfotech.ledger.reports.models;

import java.util.List;

/*@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)*/
public class CompanyAccChart {
	
	//@Id
	private Integer id;

	private String name;

	//@OneToMany
	private List<BranchAccChart> branchAccCharts;

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

	public List<BranchAccChart> getBranchAccCharts() {
		return branchAccCharts;
	}

	public void setBranchAccCharts(List<BranchAccChart> branchAccCharts) {
		this.branchAccCharts = branchAccCharts;
	}

	
}
