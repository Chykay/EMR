package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.LedgerAccDao;
import org.calminfotech.ledger.models.LedgerAccount;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LedgerAccDaoImpl implements LedgerAccDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<LedgerAccount> fetchAll(int branch_id, int company_id){
		
		List<LedgerAccount> ledgerAccounts = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerAccount WHERE organisation_id = ? AND company_id = ?")
				.setParameter(0, branch_id)
				.setParameter(1, company_id)
				.list();
		return ledgerAccounts;
	}

	@SuppressWarnings("unchecked")
	public LedgerAccount getLedgerById(int id){
		List<LedgerAccount> list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM LedgerAccount WHERE id = ?")
		.setParameter(0, id).list();
		
		if (list.size() > 0)
			return (LedgerAccount) list.get(0);
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public LedgerAccount getLedgerByAccount_no(String account_no){
		List<LedgerAccount> list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM LedgerAccount WHERE account_no = ?")
		.setParameter(0, account_no).list();
		
		if (list.size() > 0)
			return (LedgerAccount) list.get(0);
		
		return null;
	}
	
	
	
	public void save(LedgerAccount ledgerAccount){
		this.sessionFactory.getCurrentSession().save(ledgerAccount);
	}
	
	public void delete(LedgerAccount ledgerAccount){
		System.out.println("Deleting");
		this.sessionFactory.getCurrentSession().delete(ledgerAccount);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void update(LedgerAccount ledgerAccount){
		System.out.println("Updating");
		this.sessionFactory.getCurrentSession().update(ledgerAccount);
	}

}
