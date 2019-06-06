package org.calminfotech.report.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "View_topprescribeddrug")
public class PrescribedDrugReport {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "drugs_id")
	private Integer drugsid;

	@Column(name = "organisation_id")
	private int organisationId;

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Appearances")
	private Integer Appearances;

	@Column(name = "name")
	private String drugname;

	public Integer getDrugsid() {
		return drugsid;
	}

	public String getDrugname() {
		return drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}

	public void setDrugsid(Integer drugsid) {
		this.drugsid = drugsid;
	}

	public Integer getAppearances() {
		return Appearances;
	}

	public void setAppearances(Integer appearances) {
		Appearances = appearances;
	}

}
