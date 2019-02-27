package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;

public interface VwGlobalItemUnitofMeasureDao {

	List<GlobalItemUnitofMeasureVw> fetchAll();

	GlobalItemUnitofMeasureVw getVwGlobalItemUnitofMeasureById(Integer id);
	
	List<GlobalItemUnitofMeasureVw> fetchAllById(int id);
}
