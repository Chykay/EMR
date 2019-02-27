package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class GlobalUnitofMeasureList extends CustomHibernateDaoSupport{
	
	
	
	public List<GlobalUnitofMeasure> fetchAll() {
		List<GlobalUnitofMeasure> list = getHibernateTemplate().find("from GlobalUnitofMeasure order by name");
		return list;
	}
	
	
	public GlobalUnitofMeasure getGlobalUnitofMeasureById(int id) {
		List list = getHibernateTemplate().find(
				"from GlobalUnitofMeasure where id = ?", id);
		if (list.size() > 0)
			return (GlobalUnitofMeasure) list.get(0);
		return null;
	}

}
