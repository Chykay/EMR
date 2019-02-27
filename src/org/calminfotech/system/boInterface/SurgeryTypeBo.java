package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.SurgeryTypeForm;
import org.calminfotech.system.models.SurgeryType;

public interface SurgeryTypeBo {
	public SurgeryType getSurgeryTypeById(int id);
	
	public void save(SurgeryType SurgeryType);
	
	public void saveForm(SurgeryTypeForm dForm);

	public void delete(SurgeryType SurgeryType);

	public void update(SurgeryType SurgeryType);
		
	public List<SurgeryType> fetchAll();

	public List<SurgeryType> fetchAllByOrganisation();
}
