package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.GenLedgerDao;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenLedgerDaoImpl implements GenLedgerDao {

	@Autowired
	private SessionFactory sessionFactory;

	public GenLedgBalance getBalance(String account_no, int branch_id, int company_id) {
		List<GenLedgBalance> balance = sessionFactory.getCurrentSession()
				.createQuery("FROM GenLedgBalance WHERE gl_account_no = ? AND organisation_id = ? AND company_id = ? ")
				.setParameter(0, account_no)
				.setParameter(1, branch_id)
				.setParameter(2, company_id)
				.list();
				
		if (balance.size() > 0)
			return (GenLedgBalance) balance.get(0);
		
		return null;
	}

	@Override
	public void updateGLBalance(GenLedgBalance genLedgBalance) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(genLedgBalance);
	}

	

	@Override
	public void GLEntry(org.calminfotech.ledger.models.GLEntry glEntry) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(glEntry);
	}
	
}
