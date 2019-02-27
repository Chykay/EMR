package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.models.GenLedgBalance;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GLBalDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public GenLedgBalance getBalance(int branch_id, int company_id) {
		List<GenLedgBalance> balance = sessionFactory.getCurrentSession()
				.createQuery("FROM GenLedgBalance WHERE branch_id = ? AND company_id = ? ")
				.setParameter(0, branch_id)
				.setParameter(1, company_id)
				.list();
				
		if (balance.size() > 0)
			return (GenLedgBalance) balance.get(0);
		
		return null;
	}
	
}
