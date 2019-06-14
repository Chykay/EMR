package org.calminfotech.ledger.reports.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class CompanyBalSheet {
	
	@Id
	private Integer id;

	private String name;

	@OneToMany
	private List<BranchBalSheet> branchBalSheets;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BranchBalSheet> getBranchBalSheets() {
		return branchBalSheets;
	}

	public void setBranchBalSheets(List<BranchBalSheet> branchBalSheets) {
		this.branchBalSheets = branchBalSheets;
	}
}
