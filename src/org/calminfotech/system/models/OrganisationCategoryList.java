package org.calminfotech.system.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "organisation_categorylist")
public class OrganisationCategoryList {

	@Id
	@Column(name = "rowid", unique = true, nullable = false)
	private Integer rowId;

	@Column(name = "names", nullable = false)
	private String names;

	@OneToMany
	@JoinColumn(name = "organisation_category_id")
	private Set<OrganisationCompany> organisation;

	public Set<OrganisationCompany> getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Set<OrganisationCompany> organisation) {
		this.organisation = organisation;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

}
