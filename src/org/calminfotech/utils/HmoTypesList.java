package org.calminfotech.utils;

import java.util.List;

//import org.calminfotech.hmo.models.HmoType;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*
@Service
@Transactional
public class HmoTypesList {

	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;
	*public List<HmoType> fetchAll() {
	
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM HmoType").list();
		return list;
	
	}

	public HmoType getHmoTypeById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM HmoType WHERE id = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return (HmoType) list.get(0);
		return null;
	}

	public HmoType getHmoTypeByType(String type) {

		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM HmoType  WHERE type = ?")
				.setParameter(0, type).list();

		if (list.size() > 0)
			return (HmoType) list.get(0);
		return null;
	}
}*/
