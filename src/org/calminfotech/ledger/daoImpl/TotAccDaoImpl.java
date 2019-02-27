package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.TotAccDao;
import org.calminfotech.ledger.models.TotalingAccount;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TotAccDaoImpl implements TotAccDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<TotalingAccount> fetchAll(){
		
		List<TotalingAccount> totalingAccounts = sessionFactory.getCurrentSession()
				.createQuery(" from TotalingAccount")
				.list();
		return totalingAccounts;
	}
	
	public TotalingAccount getLedgerById(int id){
		List list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM TotalingAccount WHERE id = ?")
		.setParameter(0, id).list();
		
		if (list.size() > 0)
			return (TotalingAccount) list.get(0);
		
		return null;
	}
	
	public void save(TotalingAccount totalingAccount){
		this.sessionFactory.getCurrentSession().save(totalingAccount);
	}
	
	public void delete(TotalingAccount totalingAccount){
		System.out.println("Deleting");
		this.sessionFactory.getCurrentSession().delete(totalingAccount);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void update(TotalingAccount totalingAccount){
		System.out.println("Updating");
		this.sessionFactory.getCurrentSession().update(totalingAccount);
	}
}
