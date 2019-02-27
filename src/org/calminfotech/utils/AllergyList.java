package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Allergywinsearch;
import org.springframework.stereotype.Service;


@Service
public class AllergyList extends CustomHibernateDaoSupport{
	
	
	
	public List<Allergywinsearch> fetchAll() {
		List<Allergywinsearch> list = getHibernateTemplate().find("from  Allergywinsearch");
		return list;
	}
	
	public List<Allergywinsearch> fetchAllByName(String name) {
		List<Allergywinsearch> list = getHibernateTemplate().find("from  Allergywinsearch where name like ?" , name);
		return list;
	}
	
	public Allergywinsearch getAllergyById(int id) {
		List list = getHibernateTemplate().find(
				"from  Allergywinsearch where id = ?", id);
		if (list.size() > 0)
			return (Allergywinsearch) list.get(0);
		return null;
	}

}
