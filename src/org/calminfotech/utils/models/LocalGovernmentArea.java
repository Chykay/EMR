package org.calminfotech.utils.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lgas")
public class LocalGovernmentArea {

	private Integer localGovernmentAreaId;
	private String localGovernmentAreasName;
	private String localGovernmentAreasCode;
	private State state;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lga_id", unique = true, nullable = false)
	public Integer getLocalGovernmentAreaId() {
		return localGovernmentAreaId;
	}

	public void setLocalGovernmentAreaId(Integer localGovernmentAreaId) {
		this.localGovernmentAreaId = localGovernmentAreaId;
	}

	@Id
	@Column(name = "lga_code", nullable = false)
	public String getLocalGovernmentAreasCode() {
		return localGovernmentAreasCode;
	}

	public void setLocalGovernmentAreasCode(String localGovernmentAreasCode) {
		this.localGovernmentAreasCode = localGovernmentAreasCode;
	}

	@Column(name = "lga_name", nullable = false)
	public String getLocalGovernmentAreasName() {
		return localGovernmentAreasName;
	}

	public void setLocalGovernmentAreasName(String localGovernmentAreasName) {
		this.localGovernmentAreasName = localGovernmentAreasName;
	}

	@ManyToOne
	@JoinColumn(name = "state_code")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
