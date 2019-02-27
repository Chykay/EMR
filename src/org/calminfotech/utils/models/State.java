package org.calminfotech.utils.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class State {

	private Integer stateId;
	private String stateName;
	private String stateCode;
	private Country Country;
	private Set<LocalGovernmentArea> localGovernmentArea;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id", unique = true, nullable = false)
	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	@Column(name = "state_name", nullable = false)
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Id
	@Column(name = "state_code", nullable = false)
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "state_code")
	public Set<LocalGovernmentArea> getLocalGovernmentArea() {
		return localGovernmentArea;
	}

	public void setLocalGovernmentArea(
			Set<LocalGovernmentArea> localGovernmentArea) {
		this.localGovernmentArea = localGovernmentArea;
	}

	@ManyToOne
	@JoinColumn(name = "country_code")
	public Country getCountry() {
		return Country;
	}

	public void setCountry(Country country) {
		Country = country;
	}

}
