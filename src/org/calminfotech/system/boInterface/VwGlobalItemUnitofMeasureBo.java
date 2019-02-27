package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;

public interface VwGlobalItemUnitofMeasureBo {

	List<GlobalItemUnitofMeasureVw> fetchAll();

	GlobalItemUnitofMeasureVw getVwGlobalItemUnitofMeasureById(Integer id);
	
	List<GlobalItemUnitofMeasureVw> fetchAllById(int id);
}
