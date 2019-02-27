package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.SurgicalProcedures;
import org.springframework.stereotype.Service;


@Service
public class SurgicalList extends CustomHibernateDaoSupport  {
	
	public List<SurgicalProcedures> fetchAll() {
		List<SurgicalProcedures> list = getHibernateTemplate().find("from SurgicalProcedures");
		return list;
	}
	
	public SurgicalProcedures getSurgicalProceduresById(int id) {
		List list = getHibernateTemplate().find(
				"from SurgicalProcedures where id = ?", id);
		if (list.size() > 0)
			return (SurgicalProcedures) list.get(0);
		return null;
	}

}
