package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Billschemestatus;

import org.springframework.stereotype.Service;


@Service
public class BillschemestatusList extends CustomHibernateDaoSupport{
	
	
	
	public List<Billschemestatus> fetchAll() {
		List<Billschemestatus> list = getHibernateTemplate().find("from Billschemestatus");
		return list;
	}
	
	
	public Billschemestatus getBillschemestatusById(int id) {
		List list = getHibernateTemplate().find(
				"from  Billschemestatus where Billschemestatus_id = ?", id);
		if (list.size() > 0)
			return (Billschemestatus) list.get(0);
		return null;
	}

}
