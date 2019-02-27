package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Historytype;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Lifestatus;
import org.calminfotech.utils.models.Phonetype;
import org.springframework.stereotype.Service;


@Service
public class HistoryTypeList extends CustomHibernateDaoSupport{
	
	
	
	public List<Historytype> fetchAll() {
		List<Historytype> list = getHibernateTemplate().find("from  Historytype");
		return list;
	}
	
	
	public Historytype getHistoryTypeById(int id) {
		List list = getHibernateTemplate().find(
				"from Historytype where historytype_id = ?", id);
		if (list.size() > 0)
			return (Historytype) list.get(0);
		return null;
	}

}
