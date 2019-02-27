package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Staffstatus;

import org.springframework.stereotype.Service;


@Service
public class StaffstatusList extends CustomHibernateDaoSupport{
	
	
	
	public List<Staffstatus> fetchAll() {
		List<Staffstatus> list = getHibernateTemplate().find("from Staffstatus");
		return list;
	}
	
	
	public Staffstatus getStaffstatusById(int id) {
		List list = getHibernateTemplate().find(
				"from  Staffstatus where Staffstatus_id = ?", id);
		if (list.size() > 0)
			return (Staffstatus) list.get(0);
		return null;
	}

}
