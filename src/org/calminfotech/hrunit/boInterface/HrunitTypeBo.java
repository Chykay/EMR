package org.calminfotech.hrunit.boInterface;

import java.util.List;

import org.calminfotech.hrunit.forms.HrunitTypeForm;
import org.calminfotech.hrunit.models.HrunitType;

public interface HrunitTypeBo {
	public HrunitType getHrunitTypeById(int id);
	
	public void save(HrunitType HrunitType);
	
	public void saveForm(HrunitTypeForm dForm);

	public void delete(HrunitType HrunitType);

	public void update(HrunitType HrunitType);
		
	public List<HrunitType> fetchAll();

	public List<HrunitType> fetchAllByOrganisation();
}
