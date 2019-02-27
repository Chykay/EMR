package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Lifestatus;
import org.springframework.stereotype.Service;


@Service
public class LifestatusList extends CustomHibernateDaoSupport{
	
	
	
	public List<Lifestatus> fetchAll() {
		List<Lifestatus> list = getHibernateTemplate().find("from  Lifestatus");
		return list;
	}
	
	
	public Lifestatus getLifestatusById(int id) {
		List list = getHibernateTemplate().find(
				"from  Lifestatus where lifestatus_id = ?", id);
		if (list.size() > 0)
			return (Lifestatus) list.get(0);
		return null;
	}

}
