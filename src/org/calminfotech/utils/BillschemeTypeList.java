package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Billschemetype;
import org.calminfotech.utils.models.Historytype;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Lifestatus;
import org.calminfotech.utils.models.Phonetype;
import org.springframework.stereotype.Service;


@Service
public class BillschemeTypeList extends CustomHibernateDaoSupport{
	
	
	
	public List<Billschemetype> fetchAll() {
		List<Billschemetype> list = getHibernateTemplate().find("from Billschemetype");
		return list;
	}
	
	
	public Billschemetype getBillschemeTypeById(int id) {
		List list = getHibernateTemplate().find(
				"from Historytype where historytype_id = ?", id);
		if (list.size() > 0)
			return (Billschemetype) list.get(0);
		return null;
	}

}
