package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Lifestatus;
import org.calminfotech.utils.models.Phonetype;
import org.springframework.stereotype.Service;


@Service
public class PhoneTypeList extends CustomHibernateDaoSupport{
	
	
	
	public List<Phonetype> fetchAll() {
		List<Phonetype> list = getHibernateTemplate().find("from  Phonetype");
		
		return list;
	}
	
	
	public Phonetype getPhoneTypeById(int id) {
		List list = getHibernateTemplate().find(
				"from  Phonetype where phonetype_id = ?", id);
		if (list.size() > 0)
			return (Phonetype) list.get(0);
		return null;
	}

}
