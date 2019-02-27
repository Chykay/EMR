package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.GenLedgerDao;
import org.calminfotech.ledger.models.GeneralLedger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenLedgerDaoImpl implements GenLedgerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<GeneralLedger> fetchAll(){
		
		List<GeneralLedger> generalLedgers = sessionFactory.getCurrentSession()
				.createQuery(" from GeneralLedger")
				.list();
		return generalLedgers;
	}
	
	public GeneralLedger getLedgerById(int id){
		List list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM GeneralLedger WHERE id = ?")
		.setParameter(0, id).list();
		
		if (list.size() > 0)
			return (GeneralLedger) list.get(0);
		
		return null;
	}
	
	public void save(GeneralLedger generalLedger){
		this.sessionFactory.getCurrentSession().save(generalLedger);
	}
	
	public void delete(GeneralLedger generalLedger){
		System.out.println("Deleting");
		this.sessionFactory.getCurrentSession().delete(generalLedger);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void update(GeneralLedger generalLedger){
		System.out.println("Updating");
		this.sessionFactory.getCurrentSession().update(generalLedger);
	}

}
