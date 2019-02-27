package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.hrunit.models.Staffemploymentmode;
import org.calminfotech.hrunit.models.Staffgroupranking;

import org.springframework.stereotype.Service;


@Service
public class StaffgrouprankingList extends CustomHibernateDaoSupport{
	
	
	
	public List<Staffgroupranking> fetchAll() {
		List<Staffgroupranking> list = getHibernateTemplate().find("from Staffgroupranking");
		return list;
	}
	
	
	public Staffgroupranking getStaffgrouprankingById(int id) {
		List list = getHibernateTemplate().find(
				"from  Staffgroupranking where id = ?", id);
		if (list.size() > 0)
			return (Staffgroupranking) list.get(0);
		return null;
	}

}
