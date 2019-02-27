package org.calminfotech.hrunit.boInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemUnitofMeasure;

public interface UnitItemBo {

	public void save(GlobalItemUnitofMeasure unitItem);
	
	public void deleteUnitItem(GlobalItemUnitofMeasure unitItem);
	
	public void edit(GlobalItemUnitofMeasure unitItem );
	
	public GlobalItemUnitofMeasure getItemById(Integer id);
	
	public List<GlobalItemUnitofMeasure> fetchItemByUnit(GlobalItemUnitofMeasure unitItem);
	
	public GlobalItemUnitofMeasure getByUnitIdAndItemId(Integer unitId, Integer itemId);
}
