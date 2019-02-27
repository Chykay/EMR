package org.calminfotech.hrunit.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hrunit_categorylist")
public class HrunitCategoryList  {

	@Id
	@Column(name = "rowid", unique = true, nullable = false)
	private Integer rowId;

	@Column(name = "names", nullable = false)
	private String names;

	@Column(name = "organisation_id", nullable = false)
	private Integer organisationId;

		
	

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
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
