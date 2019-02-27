package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.LabtestTypeForm;
import org.calminfotech.system.models.LabtestType;

public interface LabtestTypeBo {
	public LabtestType getLabtestTypeById(int id);
	
	public void save(LabtestType LabtestType);
	
	public void saveForm(LabtestTypeForm dForm);

	public void delete(LabtestType LabtestType);

	public void update(LabtestType LabtestType);
		
	public List<LabtestType> fetchAll();

	public List<LabtestType> fetchAllByOrganisation();
}
