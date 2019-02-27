package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.springframework.stereotype.Service;


@Service
public class BloodgenotypeList extends CustomHibernateDaoSupport{
	
	
	
	public List<Bloodgenotype> fetchAll() {
		List<Bloodgenotype> list = getHibernateTemplate().find("from Bloodgenotype");
		return list;
	}
	
	
	public Bloodgenotype getBloodgenotypeById(Integer id) {
		List list = getHibernateTemplate().find(
				"from Bloodgenotype where bloodgenotype_id = ?", id);
		if (list.size() > 0)
			return (Bloodgenotype) list.get(0);
		return null;
	}

}
