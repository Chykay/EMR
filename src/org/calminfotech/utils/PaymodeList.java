package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Lifestatus;
import org.calminfotech.utils.models.Paymode;
import org.calminfotech.utils.models.Phonetype;
import org.springframework.stereotype.Service;


@Service
public class PaymodeList extends CustomHibernateDaoSupport{
	
	
	
	public List<Paymode> fetchAll() {
		List<Paymode> list = getHibernateTemplate().find("from  Paymode where is_deleted=0");
		
		return list;
	}
	
	
	public Paymode getPaymodeTypeById(int id) {
		List list = getHibernateTemplate().find(
				"from  Paymode where paymode_id = ?", id);
		if (list.size() > 0)
			return (Paymode) list.get(0);
		return null;
	}

}
