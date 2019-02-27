package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Addresstype;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Lifestatus;
import org.calminfotech.utils.models.Phonetype;
import org.springframework.stereotype.Service;


@Service
public class AddressTypeList extends CustomHibernateDaoSupport{
	
	
	
	public List<Addresstype> fetchAll() {
		List<Addresstype> list = getHibernateTemplate().find("from  Addresstype");
		return list;
	}
	
	
	public Addresstype getAddressTypeById(int id) {
		List list = getHibernateTemplate().find(
				"from Addresstype where addresstype_id = ?", id);
		if (list.size() > 0)
			return (Addresstype) list.get(0);
		return null;
	}

}
