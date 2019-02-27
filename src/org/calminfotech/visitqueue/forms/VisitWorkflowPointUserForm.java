package org.calminfotech.visitqueue.forms;

import org.hibernate.validator.constraints.Range;

public class VisitWorkflowPointUserForm {

	@Range(min = 1, message = "Must be associated with a user")
	private Integer userId;

	private Integer[] pointsId;

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the pointsId
	 */
	public Integer[] getPointsId() {
		return pointsId;
	}

	/**
	 * @param pointsId
	 *            the pointsId to set
	 */
	public void setPointsId(Integer[] pointsId) {
		this.pointsId = pointsId;
	}

}
