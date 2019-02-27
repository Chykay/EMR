package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.MaritalStatus;
import org.springframework.stereotype.Service;


@Service
public class MaritalStatusList extends CustomHibernateDaoSupport {
	
	
	public List<MaritalStatus> fetchAll() {
		List<MaritalStatus> list = getHibernateTemplate().find("from MaritalStatus ORDER BY status ASC");
		return list;
	}
	
	public MaritalStatus getMartialStatusById(int id) {
		List list = getHibernateTemplate().find(
				"from MaritalStatus where id = ?", id);
		if (list.size() > 0)
			return (MaritalStatus) list.get(0);
		return null;
	}

	

}
