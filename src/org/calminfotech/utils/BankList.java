package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.utils.models.Bank;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BankList {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Bank> fetchAll() {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Bank where is_deleted = 0  ORDER BY name ASC").list();

		return list;
	}

	public Bank getBankById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Bank WHERE id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return (Bank) list.get(0);
		return null;
	}
}
