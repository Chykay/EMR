package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.system.models.Gender;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class GenderList extends CustomHibernateDaoSupport {

	public List<Gender> fetchAll() {
		List<Gender> list = getHibernateTemplate().find("from Gender");
		return list;
	}

	public Gender getGenderById(int id) {
		List list = getHibernateTemplate().find(
				"from Gender where id = ?", id);
		if (list.size() > 0)
			return (Gender) list.get(0);
		return null;
	}
}
