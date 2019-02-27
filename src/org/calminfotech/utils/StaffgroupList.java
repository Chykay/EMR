package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.hrunit.models.Staffemploymentmode;
import org.calminfotech.hrunit.models.Staffgroup;

import org.springframework.stereotype.Service;


@Service
public class StaffgroupList extends CustomHibernateDaoSupport{
	
	
	
	public List<Staffgroup> fetchAll() {
		List<Staffgroup> list = getHibernateTemplate().find("from Staffgroup");
		return list;
	}
	
	
	public Staffgroup getStaffgroupById(int id) {
		List list = getHibernateTemplate().find(
				"from  Staffgroup where id = ?", id);
		if (list.size() > 0)
			return (Staffgroup) list.get(0);
		return null;
	}

}
