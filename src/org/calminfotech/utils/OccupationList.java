package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Occupation;
import org.springframework.stereotype.Service;


@Service
public class OccupationList extends CustomHibernateDaoSupport{
	
	
	
	public List<Occupation> fetchAll() {
		List<Occupation> list = getHibernateTemplate().find("from  Occupation");
		return list;
	}
	
	
	public Occupation getOccupationById(int id) {
		List list = getHibernateTemplate().find(
				"from  Occupation where occupation_id = ?", id);
		if (list.size() > 0)
			return (Occupation) list.get(0);
		return null;
	}

}
