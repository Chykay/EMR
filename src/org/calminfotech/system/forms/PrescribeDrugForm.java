package org.calminfotech.system.forms;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.calminfotech.utils.annotations.FormFieldName;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class PrescribeDrugForm {


	@Range(min = 1, message = "Invalid")
	private Integer visitId;

	@Valid
	@NotEmpty
	@FormFieldName(name = "Drugs") 
	private List<Integer> drugs = new ArrayList<Integer>();

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
	 * @return the drugs
	 */
	public List<Integer> getDrugs() {
		return drugs;
	}

	/**
	 * @param drugs
	 *            the drugs to set
	 */
	public void setDrugs(List<Integer> drugs) {
		this.drugs = drugs;
	}

}
