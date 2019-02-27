package org.calminfotech.visitqueue.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class VisitWorkflowPointForm {

	private Integer id;
	
	private int unitId;

	@NotEmpty(message = "Field cannot be empty!")
	private String pointName;
	
	private Integer section;

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
	 * @return the point
	 */
	public String getPointName() {
		return pointName;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPointName(String point) {
		this.pointName = point;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	
}
