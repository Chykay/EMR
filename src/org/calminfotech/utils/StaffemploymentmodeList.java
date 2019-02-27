package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.hrunit.models.Staffemploymentmode;

import org.springframework.stereotype.Service;


@Service
public class StaffemploymentmodeList extends CustomHibernateDaoSupport{
	
	
	
	public List<Staffemploymentmode> fetchAll() {
		List<Staffemploymentmode> list = getHibernateTemplate().find("from Staffemploymentmode");
		return list;
	}
	
	
	public Staffemploymentmode getStaffemploymentmodeById(int id) {
		List list = getHibernateTemplate().find(
				"from  Staffemploymentmode where id = ?", id);
		if (list.size() > 0)
			return (Staffemploymentmode) list.get(0);
		return null;
	}

}
