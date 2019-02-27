package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Drugstatus;

import org.springframework.stereotype.Service;


@Service
public class InjectionstatusList extends CustomHibernateDaoSupport{
	
	
	
	public List<Drugstatus> fetchAll() {
		List<Drugstatus> list = getHibernateTemplate().find("from Injectionstatus");
		return list;
	}
	
	
	public Drugstatus getInjectionstatusById(int id) {
		List list = getHibernateTemplate().find(
				"from  Injectionstatus where Injectionstatus_id = ?", id);
		if (list.size() > 0)
			return (Drugstatus) list.get(0);
		return null;
	}

}
