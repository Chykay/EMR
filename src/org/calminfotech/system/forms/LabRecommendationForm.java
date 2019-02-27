package org.calminfotech.system.forms;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.calminfotech.utils.annotations.FormFieldName;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class LabRecommendationForm {

	@Range(min = 1, message = "Invalid")
	private Integer visitId;

	@Valid
	@NotEmpty
	@FormFieldName(name = "Laboratory Test")  
	private List<Integer> labItems = new ArrayList<Integer>();

	/**
	 * @return the visitId
	 */
	public Integer getVisitId() {
		return visitId;
	}

	/**
	 * @param visitId
	 *            the visitId to set
	 */
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	/**
	 * @return the labItems
	 */
	public List<Integer> getLabItems() {
		return labItems;
	}

	/**
	 * @param labItems
	 *            the labItems to set
	 */
	public void setLabItems(List<Integer> labItems) {
		this.labItems = labItems;
	}

}
