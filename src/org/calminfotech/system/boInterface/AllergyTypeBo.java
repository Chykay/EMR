package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.AllergyTypeForm;
import org.calminfotech.system.models.AllergyType;

public interface AllergyTypeBo {
	public AllergyType getAllergyTypeById(int id);
	
	public void save(AllergyType AllergyType);
	
	public void saveForm(AllergyTypeForm dForm);

	public void delete(AllergyType AllergyType);

	public void update(AllergyType AllergyType);
		
	public List<AllergyType> fetchAll();

	public List<AllergyType> fetchAllByOrganisation();
}
