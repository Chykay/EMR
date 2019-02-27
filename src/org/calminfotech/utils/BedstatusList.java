package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Hmostatus;
import org.calminfotech.utils.models.Bedstatus;

import org.springframework.stereotype.Service;


@Service
public class BedstatusList extends CustomHibernateDaoSupport{
	
	
	
	public List<Bedstatus> fetchAll() {
		List<Bedstatus> list = getHibernateTemplate().find("from Bedstatus where is_deleted=0");
		return list;
	}
	
	
	public Bedstatus getBedstatusById(int id) {
		List list = getHibernateTemplate().find(
				"from  Bedstatus where Bedstatus_id = ?", id);
		if (list.size() > 0)
			return (Bedstatus) list.get(0);
		return null;
	}

}
