package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.DiseasesTypeForm;
import org.calminfotech.system.models.DiseasesType;

public interface DiseasesTypeBo {
	public DiseasesType getDiseasesTypeById(int id);
	
	public void save(DiseasesType DiseasesType);
	
	public void saveForm(DiseasesTypeForm dForm);

	public void delete(DiseasesType DiseasesType);

	public void update(DiseasesType DiseasesType);
		
	public List<DiseasesType> fetchAll();

	public List<DiseasesType> fetchAllByOrganisation();
}
