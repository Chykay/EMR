package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.ItemVw;

public interface VwItemDao {

	List<ItemVw> fetchAllByPoint(Integer pointId);
	
	ItemVw getVwItemById(Integer id);
}
