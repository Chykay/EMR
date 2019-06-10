package org.calminfotech.report.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "View_consultationcount")
public class ConsultationCount {

	@Id
	@GeneratedValue
	private String id;

	@Column(name = "title_id")
	private Integer title_id;

	@Column(name = "organisation_id")
	private int organisation_id;

	@Column(name = "created_by")
	private String created_by;

	public Integer getAppearances() {
		return Appearances;
	}

	public void setAppearances(Integer appearances) {
		Appearances = appearances;
	}

	@Column(name = "Appearances")
	private Integer Appearances;

	@Column(name = "last_name")
	private String last_name;

	@Column(name = "other_names")
	private String other_names;

	public int getOrganisation_id() {
		return organisation_id;
	}

	public void setOrganisation_id(int organisation_id) {
		this.organisation_id = organisation_id;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	/*
	 * public String getLast_name() { return last_name; }
	 * 
	 * public void setLast_name(String last_name) { this.last_name = last_name;
	 * }
	 * 
	 * public String getOther_names() { return other_names; }
	 * 
	 * public void setOther_names(String other_names) { this.other_names =
	 * other_names; }
	 */

	@Column(name = "acronym")
	private String acronym;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTitle_id() {
		return title_id;
	}

	public void setTitle_id(Integer title_id) {
		this.title_id = title_id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getOther_names() {
		return other_names;
	}

	public void setOther_names(String other_names) {
		this.other_names = other_names;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/*
	 * public Integer getTitle_id() { return title_id; }
	 * 
	 * public void setTitle_id(Integer title_id) { this.title_id = title_id; }
	 * 
	 * public Integer getAppearances() { return Appearances; }
	 * 
	 * public void setAppearances(Integer appearances) { Appearances =
	 * appearances; }
	 */
	/*
	 * public String getAcronym() { return acronym; }
	 * 
	 * public void setAcronym(String acronym) { this.acronym = acronym; }
	 */
}
