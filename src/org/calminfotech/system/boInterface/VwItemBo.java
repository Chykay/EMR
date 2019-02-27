package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.ItemVw;

public interface VwItemBo {

	List<ItemVw> fetchAllByPoint(Integer pointId);
	
	ItemVw getVwItemById(Integer id);
}
