package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class GlobalItemList extends CustomHibernateDaoSupport{
	
	
	
	public List<GlobalItem> fetchAll() {
		List<GlobalItem> list = getHibernateTemplate().find("from GlobalItem");
		return list;
	}
	
	
	public GlobalItem getGlobalItemById(int id) {
		List list = getHibernateTemplate().find(
				"from GlobalItem where id = ?", id);
		if (list.size() > 0)
			return (GlobalItem) list.get(0);
		return null;
	}

}
