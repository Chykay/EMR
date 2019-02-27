package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.system.models.GlobalItemKind;
import org.calminfotech.utils.models.Bank;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public class GlobalItemkindList {

	@Autowired
	private SessionFactory sessionFactory;

	public List<GlobalItemKind> fetchAll() {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalItemKind where is_deleted = 0  ORDER BY name ASC").list();

		return list;
	}



	public List<GlobalItemKind> fetchAllByType(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalItemKind where is_deleted = 0 and globalitemtype.globalitemTypeId  = ?   ORDER BY name ASC")
				
		.setParameter(0, id)
		.list();
		return list;
	}

	
	public GlobalItemKind getglobalkindById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalItemKind WHERE id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return (GlobalItemKind) list.get(0);
		return null;
	}
	
	
	public GlobalItemKind getglobalkindByCode(String code) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalItemKind WHERE code = ?").setParameter(0, code)
				.list();
		if (list.size() > 0)
			return (GlobalItemKind) list.get(0);
		return null;
	}
}
