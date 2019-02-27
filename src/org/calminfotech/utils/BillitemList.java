package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Billingwinsearch;
import org.springframework.stereotype.Service;


@Service
public class BillitemList extends CustomHibernateDaoSupport{
	
	
	
	public List<Billingwinsearch> fetchAll() {
		List<Billingwinsearch> list = getHibernateTemplate().find("from  Billingwinsearch");
		return list;
	}
	
	public List<Billingwinsearch> fetchAllByName(String name) {
		List<Billingwinsearch> list = getHibernateTemplate().find("from  Billingwinsearch where name like ?" , name);
		return list;
	}
	
	
	
	
	public Billingwinsearch getBillingitemById(String id) {
		List list = getHibernateTemplate().find(
				"from  Billingwinsearch where id = ?", id);
		if (list.size() > 0)
			return (Billingwinsearch) list.get(0);
		return null;
	}

}
