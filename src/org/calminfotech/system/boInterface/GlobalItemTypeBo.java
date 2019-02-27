package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.GlobalItemTypeForm;
import org.calminfotech.system.models.GlobalItemType;

public interface GlobalItemTypeBo {
	public GlobalItemType getGlobalItemTypeById(int id);
	
	public void save(GlobalItemType globalItemType);
	
	public void saveForm(GlobalItemTypeForm gTForm);

	public void delete(GlobalItemType globalItemType);

	public void update(GlobalItemType globalItemType);
		
	public List<GlobalItemType> fetchAll();

	public List<GlobalItemType> fetchAllByOrganisation();
}
