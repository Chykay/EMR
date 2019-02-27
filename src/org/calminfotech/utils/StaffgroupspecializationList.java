package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.hrunit.models.Staffemploymentmode;
import org.calminfotech.hrunit.models.Staffgroupspecialization;

import org.springframework.stereotype.Service;


@Service
public class StaffgroupspecializationList extends CustomHibernateDaoSupport{
	
	
	
	public List<Staffgroupspecialization> fetchAll() {
		List<Staffgroupspecialization> list = getHibernateTemplate().find("from Staffgroupspecialization");
		return list;
	}
	
	
	public Staffgroupspecialization getStaffgroupspecializationById(int id) {
		List list = getHibernateTemplate().find(
				"from  Staffgroupspecialization where id = ?", id);
		if (list.size() > 0)
			return (Staffgroupspecialization) list.get(0);
		return null;
	}

}
