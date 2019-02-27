package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Hmostatus;
import org.calminfotech.utils.models.Prescribedstatus;

import org.springframework.stereotype.Service;


@Service
public class PrescribedstatusList extends CustomHibernateDaoSupport{
	
	
	
	public List<Prescribedstatus> fetchAll() {
		List<Prescribedstatus> list = getHibernateTemplate().find("from Prescribedstatus where is_deleted=0");
		return list;
	}
	
	
	public Prescribedstatus getPrescribedstatusById(int id) {
		List list = getHibernateTemplate().find(
				"from  Prescribedstatus where Prescribedstatus_id = ?", id);
		if (list.size() > 0)
			return (Prescribedstatus) list.get(0);
		System.out.print("am returning status");
		return null;
	}

}
