package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Hmostatus;

import org.springframework.stereotype.Service;


@Service
public class HmostatusList extends CustomHibernateDaoSupport{
	
	
	
	public List<Hmostatus> fetchAll() {
		List<Hmostatus> list = getHibernateTemplate().find("from Hmostatus");
		return list;
	}
	
	
	public Hmostatus getHmostatusById(int id) {
		List list = getHibernateTemplate().find(
				"from  Hmostatus where Hmostatus_id = ?", id);
		if (list.size() > 0)
			return (Hmostatus) list.get(0);
		return null;
	}

}
