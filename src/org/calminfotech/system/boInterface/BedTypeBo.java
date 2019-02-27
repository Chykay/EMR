package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.BedTypeForm;
import org.calminfotech.system.models.BedType;

public interface BedTypeBo {
	public BedType getBedTypeById(int id);
	
	public void save(BedType BedType);
	
	public void saveForm(BedTypeForm dForm);

	public void delete(BedType BedType);

	public void update(BedType BedType);
		
	public List<BedType> fetchAll();

	public List<BedType> fetchAllByOrganisation();
}
