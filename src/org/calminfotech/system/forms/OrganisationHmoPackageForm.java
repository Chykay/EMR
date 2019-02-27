package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class OrganisationHmoPackageForm {

	private Integer id;

	@Range(min = 1, message = "Select a HMO")
	private Integer hmoId;

	@NotEmpty(message = "Field cannot be empty")
	private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the hmoId
	 */
	public Integer getHmoId() {
		return hmoId;
	}

	/**
	 * @param hmoId
	 *            the hmoId to set
	 */
	public void setHmoId(Integer hmoId) {
		this.hmoId = hmoId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
