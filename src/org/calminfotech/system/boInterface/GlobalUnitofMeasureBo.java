package org.calminfotech.system.boInterface;

import java.util.List;

//import org.calminfotech.inventory.models.GlobalItemUnitofMeasure;
import org.calminfotech.system.models.GlobalUnitofMeasure;

public interface GlobalUnitofMeasureBo {

	List<GlobalUnitofMeasure> fetchAll();
	
	GlobalUnitofMeasure getUnitofMeasureById(int id);
	
	void save(GlobalUnitofMeasure unitofMeasure);
	
	void delete(GlobalUnitofMeasure unitofMeasure);
	
	void update(GlobalUnitofMeasure unitofMeasure);

}
