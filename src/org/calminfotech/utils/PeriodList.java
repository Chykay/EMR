package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Period;
import org.springframework.stereotype.Service;


@Service
public class PeriodList extends CustomHibernateDaoSupport{
	
	
	
	public List<Period> fetchAll() {
		List<Period> list = getHibernateTemplate().find("from  Period");
		return list;
	}
	
	
	public Period getPeriodById(int id) {
		List list = getHibernateTemplate().find(
				"from  Period where period_id = ?", id);
		if (list.size() > 0)
			return (Period) list.get(0);
		return null;
	}

}
