package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.models.LedgerType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LedgerTypesImpl {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<LedgerType> fetchAll() {
		List<LedgerType> ledgers = sessionFactory.getCurrentSession()
				  .createQuery("from LedgerType")
				  .list();
		
	return ledgers;
	}
}
